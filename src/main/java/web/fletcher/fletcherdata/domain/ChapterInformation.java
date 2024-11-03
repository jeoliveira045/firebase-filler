package web.fletcher.fletcherdata.domain;

import lombok.Data;

@Data
public class ChapterInformation {

    private String id;

    private String manga;

    private String title;

    private Long create_at;

    private Long update_at;
}
