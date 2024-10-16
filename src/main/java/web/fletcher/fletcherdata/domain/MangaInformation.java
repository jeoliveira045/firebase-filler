package web.fletcher.fletcherdata.domain;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class MangaInformation {
    private String id;

    private String title;

    private String sub_title;

    private String status;

    private String thumb;

    private String summary;

    private List<String> authors;

    private List<String> genres;

    private Boolean nsfw;

    private String type;

    private Integer total_chapter;

    private Long created_at;

    private Long update_at;

}
