package org.car.catalog.viewmodel;

import java.util.List;

import org.car.catalog.model.dto.CarDto;
import org.car.catalog.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
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
    private List<CarDto> carList;
    private CarDto selectedCar;

    // 新增資訊
    // 是否顯示新增 Dialog
    private boolean showInsertDialog = false;
    private CarDto newCar;

    @WireVariable
    private CarService carService;

    @Init
    public void init() {
        newCar = new CarDto();
    }

    @Command
    @NotifyChange({"carList", "selectedCar", "activePage", "totalSize"})
    public void search(@BindingParam("type") String type) {
        if ("search".equals(type)) {
            activePage = DEFAULT_ACTIVE_PAGE;
        }
        Pageable pageable = PageRequest.of(activePage, pageSize, Sort.by(Sort.Direction.ASC, "id"));
        Page<CarDto> cars = carService.search(keyword, pageable);
        carList = cars.getContent();
        totalSize = (int) cars.getTotalElements();

        // 執行新的查詢時，清空 selectedCar
        selectedCar = null;
    }

    @Command
    @NotifyChange("showInsertDialog")
    public void showInsertDialog() {
        showInsertDialog = true;
    }

    @Command
    @NotifyChange({"showInsertDialog", "newCar"})
    public void closeInsertDialog() {
        showInsertDialog = false;

        // 新增視窗關閉後，重新初始化 Car 物件
        newCar = new CarDto();
    }

    @Command
    @NotifyChange("carList")
    public void insert(@ContextParam(ContextType.BINDER) Binder binder) {
        carService.insert(newCar);

        // 新增完成顯示新增成功對話框，點選確認後透過 Binder 呼叫 @command('showInsertDialog')
        Messagebox.show("新增成功",
                new Messagebox.Button[]{Messagebox.Button.OK},
                clickEvent -> binder.postCommand("closeInsertDialog", null));
    }

}
