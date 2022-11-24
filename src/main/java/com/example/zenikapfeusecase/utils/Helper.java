package com.example.zenikapfeusecase.utils;

import com.example.zenikapfeusecase.entity.Equipment;
import com.example.zenikapfeusecase.enumerations.Equip;
import com.example.zenikapfeusecase.enumerations.MType;

import java.util.Arrays;
import java.util.List;

public class Helper {

    public static  List<Equipment> getEquipmentsNeededForEachMeeting(MType MType){
        switch (MType){
            case VC:
                return Arrays.asList (
                        new Equipment (1L, Equip.ECRAN),
                        new Equipment (2L, Equip.PIEUVRE),
                        new Equipment (3L , Equip.WEBCAM)
                );

            case SPEC:
                return Arrays.asList (
                        new Equipment (1l ,Equip.TABLEAU)
                );
            case RC:
                return Arrays.asList (
                        new Equipment (1L, Equip.ECRAN),
                        new Equipment (2L, Equip.PIEUVRE),
                        new Equipment (3L, Equip.TABLEAU)
                );
        }
        return null ;
    }

}
