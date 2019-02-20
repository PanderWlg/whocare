package com.womow.toc.whocare.clbx.model;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author changqingshun
 * @since 2019-01-29
 */
@TableName(value="t_pmis_bx_cl",resultMap = "BaseResultMap")
public class BxClDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id_")
    private String id_;

    /**
     * 报销编号
     */
    @TableField("code_")
    private String code_;

    /**
     * 项目id
     */
    @TableField("proj_id_")
    private String proj_id_;

    /**
     * 项目编号
     */
    @TableField("proj_code_")
    private String proj_code_;

    /**
     * 项目名称
     */
    @TableField("proj_name_")
    private String proj_name_;

    /**
     * 报销人id
     */
    @TableField("bxr_ids_")
    private String bxr_ids_;

    /**
     * 报销人姓名
     */
    @TableField("bxr_names_")
    private String bxr_names_;

    /**
     * 补助总金额
     */
    @TableField("subsidy_amount_")
    private BigDecimal subsidy_amount_;

    /**
     * 报销总金额
     */
    @TableField("total_amount_")
    private BigDecimal total_amount_;

    /**
     * pmo区域
     */
    @TableField("pmo_")
    private String pmo_;

    /**
     * 创建人id
     */
    @TableField("creator_id_")
    private String creator_id_;

    /**
     * 发起人姓名
     */
    @TableField("creator_name_")
    private String creator_name_;

    /**
     * 创建时间
     */
    @TableField("create_time_")
    private String create_time_;

    /**
     * 特定类型标示['':无，cz：冲账]
     */
    @TableField("type_flag_")
    private String type_flag_;

    /**
     * 流程实例id
     */
    @TableField("proc_id_")
    private String proc_id_;

    /**
     * 流程状态
     */
    @TableField("proc_status_")
    private String proc_status_;

    /**
     * 状态
     */
    @TableField("status_")
    private String status_;

    /**
     * 终止时间
     */
    @TableField("terminated_time_")
    private String terminated_time_;

    /**
     * 终止原因
     */
    @TableField("terminated_reason_")
    private String terminated_reason_;

    /**
     * 是否可打印[1：可以，0：不可以]
     */
    @TableField("is_can_print_")
    private String is_can_print_;

    /**
     * 查询标识，9：待付款
     */
    @TableField("query_flag_")
    private String query_flag_;

    /**
     * 现金报销金额
     */
    @TableField("xj_amount_")
    private BigDecimal xj_amount_;

    /**
     * 现金报销说明
     */
    @TableField("xj_tip_")
    private String xj_tip_;

    /**
     * 冲账大json
     */
    @TableField("cz_json_")
    private String cz_json_;

    public String getId_() {
        return id_;
    }

    public BxClDto setId_(String id_) {
        this.id_ = id_;
        return this;
    }
    public String getCode_() {
        return code_;
    }

    public BxClDto setCode_(String code_) {
        this.code_ = code_;
        return this;
    }
    public String getProj_id_() {
        return proj_id_;
    }

    public BxClDto setProj_id_(String proj_id_) {
        this.proj_id_ = proj_id_;
        return this;
    }
    public String getProj_code_() {
        return proj_code_;
    }

    public BxClDto setProj_code_(String proj_code_) {
        this.proj_code_ = proj_code_;
        return this;
    }
    public String getProj_name_() {
        return proj_name_;
    }

    public BxClDto setProj_name_(String proj_name_) {
        this.proj_name_ = proj_name_;
        return this;
    }
    public String getBxr_ids_() {
        return bxr_ids_;
    }

    public BxClDto setBxr_ids_(String bxr_ids_) {
        this.bxr_ids_ = bxr_ids_;
        return this;
    }
    public String getBxr_names_() {
        return bxr_names_;
    }

    public BxClDto setBxr_names_(String bxr_names_) {
        this.bxr_names_ = bxr_names_;
        return this;
    }
    public BigDecimal getSubsidy_amount_() {
        return subsidy_amount_;
    }

    public BxClDto setSubsidy_amount_(BigDecimal subsidy_amount_) {
        this.subsidy_amount_ = subsidy_amount_;
        return this;
    }
    public BigDecimal getTotal_amount_() {
        return total_amount_;
    }

    public BxClDto setTotal_amount_(BigDecimal total_amount_) {
        this.total_amount_ = total_amount_;
        return this;
    }
    public String getPmo_() {
        return pmo_;
    }

    public BxClDto setPmo_(String pmo_) {
        this.pmo_ = pmo_;
        return this;
    }
    public String getCreator_id_() {
        return creator_id_;
    }

    public BxClDto setCreator_id_(String creator_id_) {
        this.creator_id_ = creator_id_;
        return this;
    }
    public String getCreator_name_() {
        return creator_name_;
    }

    public BxClDto setCreator_name_(String creator_name_) {
        this.creator_name_ = creator_name_;
        return this;
    }
    public String getCreate_time_() {
        return create_time_;
    }

    public BxClDto setCreate_time_(String create_time_) {
        this.create_time_ = create_time_;
        return this;
    }
    public String getType_flag_() {
        return type_flag_;
    }

    public BxClDto setType_flag_(String type_flag_) {
        this.type_flag_ = type_flag_;
        return this;
    }
    public String getProc_id_() {
        return proc_id_;
    }

    public BxClDto setProc_id_(String proc_id_) {
        this.proc_id_ = proc_id_;
        return this;
    }
    public String getProc_status_() {
        return proc_status_;
    }

    public BxClDto setProc_status_(String proc_status_) {
        this.proc_status_ = proc_status_;
        return this;
    }
    public String getStatus_() {
        return status_;
    }

    public BxClDto setStatus_(String status_) {
        this.status_ = status_;
        return this;
    }
    public String getTerminated_time_() {
        return terminated_time_;
    }

    public BxClDto setTerminated_time_(String terminated_time_) {
        this.terminated_time_ = terminated_time_;
        return this;
    }
    public String getTerminated_reason_() {
        return terminated_reason_;
    }

    public BxClDto setTerminated_reason_(String terminated_reason_) {
        this.terminated_reason_ = terminated_reason_;
        return this;
    }
    public String getIs_can_print_() {
        return is_can_print_;
    }

    public BxClDto setIs_can_print_(String is_can_print_) {
        this.is_can_print_ = is_can_print_;
        return this;
    }
    public String getQuery_flag_() {
        return query_flag_;
    }

    public BxClDto setQuery_flag_(String query_flag_) {
        this.query_flag_ = query_flag_;
        return this;
    }
    public BigDecimal getXj_amount_() {
        return xj_amount_;
    }

    public BxClDto setXj_amount_(BigDecimal xj_amount_) {
        this.xj_amount_ = xj_amount_;
        return this;
    }
    public String getXj_tip_() {
        return xj_tip_;
    }

    public BxClDto setXj_tip_(String xj_tip_) {
        this.xj_tip_ = xj_tip_;
        return this;
    }
    public String getCz_json_() {
        return cz_json_;
    }

    public BxClDto setCz_json_(String cz_json_) {
        this.cz_json_ = cz_json_;
        return this;
    }

    @Override
    public String toString() {
        return "BxClDto{" +
        "id_=" + id_ +
        ", code_=" + code_ +
        ", proj_id_=" + proj_id_ +
        ", proj_code_=" + proj_code_ +
        ", proj_name_=" + proj_name_ +
        ", bxr_ids_=" + bxr_ids_ +
        ", bxr_names_=" + bxr_names_ +
        ", subsidy_amount_=" + subsidy_amount_ +
        ", total_amount_=" + total_amount_ +
        ", pmo_=" + pmo_ +
        ", creator_id_=" + creator_id_ +
        ", creator_name_=" + creator_name_ +
        ", create_time_=" + create_time_ +
        ", type_flag_=" + type_flag_ +
        ", proc_id_=" + proc_id_ +
        ", proc_status_=" + proc_status_ +
        ", status_=" + status_ +
        ", terminated_time_=" + terminated_time_ +
        ", terminated_reason_=" + terminated_reason_ +
        ", is_can_print_=" + is_can_print_ +
        ", query_flag_=" + query_flag_ +
        ", xj_amount_=" + xj_amount_ +
        ", xj_tip_=" + xj_tip_ +
        ", cz_json_=" + cz_json_ +
        "}";
    }
}
