package com.womow.toc.whocare.clbx.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author changqingshun
 * @since 2019-01-29
 */
@TableName(value = "t_pmis_chuxing_copy1", resultMap = "BaseResultMap")
public class ChuxingDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id_", type = IdType.UUID)
    private String id_;

    @TableField("bx_id_")
    private String bx_id_;

    @TableField("didian_")
    private String didian_;

    /**
     * 出行目的
     */
    @TableField("mudi_")
    private String mudi_;

    @TableField("proposer_")
    private String proposer_;

    @TableField(value = "proposer_name_", condition = SqlCondition.LIKE_RIGHT)
    private String proposer_name_;

    @TableField("st_")
    private String st_;

    @TableField("et_")
    private String et_;

    @TableField("txr_")
    private String txr_;

    @TableField("beizhu_")
    private String beizhu_;

    @TableField(value = "create_time_", fill = FieldFill.INSERT)
    private String create_time_;

    @TableField("status_")
    private String status_;

    public String getId_() {
        return id_;
    }

    public ChuxingDto setId_(String id_) {
        this.id_ = id_;
        return this;
    }

    public String getBx_id_() {
        return bx_id_;
    }

    public ChuxingDto setBx_id_(String bx_id_) {
        this.bx_id_ = bx_id_;
        return this;
    }

    public String getDidian_() {
        return didian_;
    }

    public ChuxingDto setDidian_(String didian_) {
        this.didian_ = didian_;
        return this;
    }

    public String getMudi_() {
        return mudi_;
    }

    public ChuxingDto setMudi_(String mudi_) {
        this.mudi_ = mudi_;
        return this;
    }

    public String getProposer_() {
        return proposer_;
    }

    public ChuxingDto setProposer_(String proposer_) {
        this.proposer_ = proposer_;
        return this;
    }

    public String getProposer_name_() {
        return proposer_name_;
    }

    public ChuxingDto setProposer_name_(String proposer_name_) {
        this.proposer_name_ = proposer_name_;
        return this;
    }

    public String getSt_() {
        return st_;
    }

    public ChuxingDto setSt_(String st_) {
        this.st_ = st_;
        return this;
    }

    public String getEt_() {
        return et_;
    }

    public ChuxingDto setEt_(String et_) {
        this.et_ = et_;
        return this;
    }

    public String getTxr_() {
        return txr_;
    }

    public ChuxingDto setTxr_(String txr_) {
        this.txr_ = txr_;
        return this;
    }

    public String getBeizhu_() {
        return beizhu_;
    }

    public ChuxingDto setBeizhu_(String beizhu_) {
        this.beizhu_ = beizhu_;
        return this;
    }

    public String getCreate_time_() {
        return create_time_;
    }

    public ChuxingDto setCreate_time_(String create_time_) {
        this.create_time_ = create_time_;
        return this;
    }

    public String getStatus_() {
        return status_;
    }

    public ChuxingDto setStatus_(String status_) {
        this.status_ = status_;
        return this;
    }

    @Override
    public String toString() {
        return "ChuxingDto{" +
                "id_=" + id_ +
                ", bx_id_=" + bx_id_ +
                ", didian_=" + didian_ +
                ", mudi_=" + mudi_ +
                ", proposer_=" + proposer_ +
                ", proposer_name_=" + proposer_name_ +
                ", st_=" + st_ +
                ", et_=" + et_ +
                ", txr_=" + txr_ +
                ", beizhu_=" + beizhu_ +
                ", create_time_=" + create_time_ +
                ", status_=" + status_ +
                "}";
    }
}
