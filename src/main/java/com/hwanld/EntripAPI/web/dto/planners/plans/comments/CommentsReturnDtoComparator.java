package com.hwanld.EntripAPI.web.dto.planners.plans.comments;

import java.util.Comparator;

public class CommentsReturnDtoComparator implements Comparator<CommentsReturnDto> {
    @Override
    public int compare (CommentsReturnDto a, CommentsReturnDto b) {
        if (a.getComment_id() > b.getComment_id()) return 1;
        if (a.getComment_id() < b.getComment_id()) return -1;
        return 0;
    }
}
