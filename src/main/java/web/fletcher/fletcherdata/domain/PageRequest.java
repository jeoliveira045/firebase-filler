package web.fletcher.fletcherdata.domain;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class PageRequest<T> {
    private BigInteger code;

    private List<T> data;
}
