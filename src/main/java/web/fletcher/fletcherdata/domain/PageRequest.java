package web.fletcher.fletcherdata.domain;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class PageRequest {
    private BigInteger code;

    private List<MangaInformation> data;
}
