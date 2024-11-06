package web.fletcher.fletcherdata.domain.dto;

import lombok.Data;
import web.fletcher.fletcherdata.domain.ChapterInformation;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
public class MangaInformationDTO {
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

    private Long create_at;

    private Long update_at;

    private List<ChapterInformation> chapterInformationList = new ArrayList<>();

}
