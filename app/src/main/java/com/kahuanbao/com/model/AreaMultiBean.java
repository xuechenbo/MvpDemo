package com.kahuanbao.com.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.contrarywind.interfaces.IPickerViewData;

/**
 * Created by Administrator on 2019/4/1.
 *
 */

public class AreaMultiBean implements IPickerViewData, MultiItemEntity {

    /**
     * id : 10C97461C64E4C06BFC2CCCDEDA259C0
     * divisionName : 青海省
     * areaCode : 8500
     * divisionCode : 630000
     */

    private String id;
    private String divisionName;
    private String areaCode;
    private String divisionCode;



    private int itemType=1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getDivisionCode() {
        return divisionCode;
    }

    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    @Override
    public String getPickerViewText() {
        return divisionName;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
