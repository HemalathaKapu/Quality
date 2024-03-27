package org.siemens.quality.dao.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name= "process_parameters", schema = "quality")
public class ProcessParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "FinishedGoods")
    @TableGenerator(name = "FinishedGoods",table = "t_idgenerator",initialValue = 100)
    private Integer id;
    private String parameter;
    private String value;
    private String units;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fg")
    private FinishedGood finishedGood;

}
