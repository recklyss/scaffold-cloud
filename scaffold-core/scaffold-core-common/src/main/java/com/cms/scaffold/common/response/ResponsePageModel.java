package com.cms.scaffold.common.response;

import com.cms.scaffold.common.base.BaseStatusCode;
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
        super(BaseStatusCode.SUCCESS, null);
        this.rows = data;
        this.total = total;
    }
}
