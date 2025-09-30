package edu.tennis.score.homasapienss.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PaginationDTO<T> {
    private List<T> paginationList;
    private Integer totalPages;
    private Integer currentPage;
}
