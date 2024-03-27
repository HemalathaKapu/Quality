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
@Table(name= "fg_type_mapper", schema = "quality")
public class FgTypeMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "FgTypeMapper")
    @TableGenerator(name = "FgTypeMapper",table = "t_idgenerator",initialValue = 100)
    private Integer id;
    private String itemCode;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fg_type")
    private FgTypeMaster fgType;
}
