package com.winterchen.common.base;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author winterchen
 * @version 1.0
 * @date 2021/1/19 2:42 下午
 **/
@Data
@SuperBuilder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ApiModel("基本类")
public class BaseModel implements Serializable {

    @TableField("create_by")
    @ApiModelProperty(value = "创建人", hidden = true)
    private String createBy;

    @TableField("update_by")
    @ApiModelProperty(value = "更新人", hidden = true)
    private String updatedBy;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Timestamp createTime;

    @TableField("update_time")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Timestamp updateTime;
    

    /* 分组校验 */
    public @interface Create {}

    /* 分组校验 */
    public @interface Update {}
}
