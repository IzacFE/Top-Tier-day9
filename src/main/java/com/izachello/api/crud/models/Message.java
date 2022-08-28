package com.izachello.api.crud.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="messages", schema = "public")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_message_id_seq")
    @SequenceGenerator(name = "generator_message_id_seq",sequenceName = "message_id_seq", schema = "public", allocationSize = 1)
    private long messageId;

    @Column(name = "message")
    private String message;
}
