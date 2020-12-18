package entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class POrder implements Serializable {

    private Long id;

    private Long serialNo;

    private BigDecimal predictSum;

    private BigDecimal discountsSum;

    private BigDecimal logisticsSum;

    private BigDecimal actualSum;

    private Long payMethodId;

    private Long logisticsId;

    private Integer orderState;

    private Integer logisticsState;

    private Long operatorId;

    private String remark;

    private Long operationId;

    private Date createTime;

    private Date changeTime;

    private Date logisticsTime;

    private Date successTime;

    private Integer sign;

    private static final long serialVersionUID = 1L;
}
