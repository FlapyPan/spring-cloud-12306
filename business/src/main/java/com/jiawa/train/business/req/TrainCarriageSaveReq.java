package com.jiawa.train.business.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class TrainCarriageSaveReq {

    /**
     * id
     */
    private Long id;

    /**
     * 车次编号
     */
    @NotBlank(message = "【车次编号】不能为空")
    private String trainCode;

    /**
     * 厢号
     */
    @NotNull(message = "【厢号】不能为空")
    private Integer index;

    /**
     * 座位类型|枚举[SeatTypeEnum]
     */
    @NotBlank(message = "【座位类型】不能为空")
    private String seatType;

    /**
     * 座位数
     */
    // @NotNull(message = "【座位数】不能为空")
    private Integer seatCount;

    /**
     * 排数
     */
    @NotNull(message = "【排数】不能为空")
    private Integer rowCount;

    /**
     * 列数
     */
    // @NotNull(message = "【列数】不能为空")
    private Integer colCount;

    /**
     * 新增时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public Integer getColCount() {
        return colCount;
    }

    public void setColCount(Integer colCount) {
        this.colCount = colCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", trainCode=").append(trainCode);
        sb.append(", index=").append(index);
        sb.append(", seatType=").append(seatType);
        sb.append(", seatCount=").append(seatCount);
        sb.append(", rowCount=").append(rowCount);
        sb.append(", colCount=").append(colCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}
