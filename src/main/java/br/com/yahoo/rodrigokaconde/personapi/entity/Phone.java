package br.com.yahoo.rodrigokaconde.personapi.entity;

import br.com.yahoo.rodrigokaconde.personapi.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//lombok
@Entity//classe é um entidade
@Data//geter e setters
@Builder
@AllArgsConstructor//contrutores
@NoArgsConstructor
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)//campo obrigatorio
    private PhoneType type;

    @Column(nullable = false)
    private String number;
}
