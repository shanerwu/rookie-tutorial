package org.car.catalog.viewmodel;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class FormValidator extends AbstractValidator {

    @Override
    public void validate(ValidationContext ctx) {
        Map<String, Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
        validateModel(ctx, (String) beanProps.get("model").getValue());
        validateMake(ctx, (String) beanProps.get("make").getValue());
        validatePrice(ctx, (Integer) beanProps.get("price").getValue());
        validateAmount(ctx, (Integer) beanProps.get("amount").getValue());
        validateDescription(ctx, (String) beanProps.get("description").getValue());
    }

    private void validateModel(ValidationContext ctx, String value) {
        if (value == null) {
            this.addInvalidMessage(ctx, "modelError", "名稱不可空白");
        }
    }

    private void validateMake(ValidationContext ctx, String value) {
        if (value == null) {
            this.addInvalidMessage(ctx, "makeError", "製造商不可空白");
        }
    }

    private void validatePrice(ValidationContext ctx, Integer value) {
        if (value == null) {
            this.addInvalidMessage(ctx, "priceError", "價格不可空白");
        } else if (value <= 0) {
            this.addInvalidMessage(ctx, "priceError", "價格必須 > 0");
        } else if (String.valueOf(value).length() > 7) {
            this.addInvalidMessage(ctx, "priceError", "價格不得超過 7 位數");
        }
    }

    private void validateAmount(ValidationContext ctx, Integer value) {
        if (value == null) {
            this.addInvalidMessage(ctx, "amountError", "數量不可空白");
        } else if (value < 0) {
            this.addInvalidMessage(ctx, "amountError", "數量必須 >= 0");
        } else if (String.valueOf(value).length() > 3) {
            this.addInvalidMessage(ctx, "amountError", "數量不得超過 3 位數");
        }
    }

    private void validateDescription(ValidationContext ctx, String value) {
        if (value == null) {
            this.addInvalidMessage(ctx, "descriptionError", "描述不可空白");
        }
    }

}
