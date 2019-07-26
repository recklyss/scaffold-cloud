package com.cms.scaffold.common.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author zhangjiaheng
 **/
@Getter
@Setter
public class ResponsePageModel<T> extends ResponseModel {

    private List<T> rows;

    private Long total;

    public ResponsePageModel(List<T> data, Long total) {
        super(STATUS_CODE.SUCCESS, null);
        this.rows = data;
        this.total = total;
    }
}
