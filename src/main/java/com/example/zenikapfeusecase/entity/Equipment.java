package com.example.zenikapfeusecase.entity;

import com.example.zenikapfeusecase.enumerations.Equip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Enumerated(EnumType.STRING)
    private Equip equipmentName ;


    public Equipment(Equip equipmentName) {
        this.equipmentName = equipmentName;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

		/* Check if o is an instance of Complex or not
		"null instanceof [type]" also returns false */
        if (!(o instanceof Equipment)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Equipment e = (Equipment) o;

        // Compare the data members and return accordingly
        return  this.equipmentName.equals (e.equipmentName);
    }

}
