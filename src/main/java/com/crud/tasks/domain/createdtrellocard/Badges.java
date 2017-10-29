package com.crud.tasks.domain.createdtrellocard;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Badges {
    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private AttachmentsByType attachmentsByType;
}
