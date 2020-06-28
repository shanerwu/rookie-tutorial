package org.car.sale.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.car.catalog.service.Car;
import org.car.catalog.service.OrderCarResult;
import org.car.catalog.service.SearchCarResult;
import org.car.sale.service.CarSaleService;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@VariableResolver(DelegatingVariableResolver.class)
public class ManagementViewModel {

    private static final Integer DEFAULT_ACTIVE_PAGE = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 2;

    // 分頁資訊
    private Integer activePage = DEFAULT_ACTIVE_PAGE;
    private Integer pageSize = DEFAULT_PAGE_SIZE;
    private Integer totalSize;

    // 搜尋資訊
    private String keyword;
    private List<Car> carList;
    private Car selectedCar;

    // 訂購資訊
    // 是否顯示訂購 Dialog
    private boolean showOrderDialog = false;
    private Car selectedOrderCar;
    private Integer orderAmount = 1;
    private List<Integer> orderAmounts;

    @WireVariable
    private CarSaleService carSaleService;

    @Command
    @NotifyChange({"carList", "selectedCar", "activePage", "totalSize"})
    public void search(@BindingParam("type") String type) {
        if ("search".equals(type)) {
            activePage = DEFAULT_ACTIVE_PAGE;
        }
        SearchCarResult result = carSaleService.findCar(keyword, activePage, pageSize);
        carList = result.getCars();
        totalSize = result.getTotalSize();
        selectedCar = null;
    }

    @Command
    @NotifyChange("carList")
    public void order(@ContextParam(ContextType.BINDER) Binder binder) {
        carList = null;
        OrderCarResult result = carSaleService.order(selectedOrderCar.getId(), orderAmount);
        if (result.isSuccess()) {
            Messagebox.show("訂購成功",
                    new Messagebox.Button[]{Messagebox.Button.OK},
                    clickEvent -> binder.postCommand("closeOrderDialog", null));
        } else {
            Messagebox.show("訂購失敗",
                    new Messagebox.Button[]{Messagebox.Button.OK},
                    clickEvent -> binder.postCommand("closeOrderDialog", null));
        }
    }

    @Command
    @NotifyChange({"showOrderDialog", "selectedOrderCar", "orderAmounts"})
    public void showOrderDialog(@BindingParam("selectedOrderCar") Car selectedOrderCar) {
        this.selectedOrderCar = selectedOrderCar;
        orderAmounts = new ArrayList<>();
        for (int i = 1; i <= selectedOrderCar.getAmount(); i++) {
            orderAmounts.add(i);
        }
        showOrderDialog = true;
    }

    @Command
    @NotifyChange({"showOrderDialog", "orderAmount"})
    public void closeOrderDialog() {
        orderAmount = 1;
        showOrderDialog = false;
    }

}
