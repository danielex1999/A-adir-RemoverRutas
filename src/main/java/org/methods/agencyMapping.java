package org.methods;

import java.util.HashMap;
import java.util.Map;

public class agencyMapping {
    private static final Map<String, String> AGENCIA_MAPPING = new HashMap<>();

    public agencyMapping() {
        /*Lima*/
        AGENCIA_MAPPING.put("TB_CAL", "14161 - CALLAO");
        AGENCIA_MAPPING.put("CONS", "14161 - CALLAO");
        AGENCIA_MAPPING.put("MAYOR", "14161 - CALLAO");
        AGENCIA_MAPPING.put("TB_STA", "14162 - SANTA ANITA");
        AGENCIA_MAPPING.put("TB_TRU", "14165 - TRUJILLO");
        AGENCIA_MAPPING.put("TB_INDEP", "14175 - INDEPENDENCIA");
        AGENCIA_MAPPING.put("TB_VIRTUAL INDEP", "14175 - INDEPENDENCIA");
        AGENCIA_MAPPING.put("TB_VIRTUAL SJL", "14176 - SAN JUAN DE LURIGANCHO");
        AGENCIA_MAPPING.put("TB_SJL", "14176 - SAN JUAN DE LURIGANCHO");
        AGENCIA_MAPPING.put("TB_VIRTUAL VES", "14177 - VILLA EL SALVADOR");
        // Agrega más correspondencias según sea necesario

        /*Provincia*/
        AGENCIA_MAPPING.put("14169 - AREQUIPA", "14169 - AREQUIPA");
        AGENCIA_MAPPING.put("TB_ARE", "14169 - AREQUIPA");
        AGENCIA_MAPPING.put("TB_HUA", "14171 - HUANCAYO");
        AGENCIA_MAPPING.put("TB_CHIM", "14178 - CHIMBOTE");
        AGENCIA_MAPPING.put("TB_ICA", "14172 - ICA");
        AGENCIA_MAPPING.put("TB_PIU", "14170 - PIURA");
        AGENCIA_MAPPING.put("TB_CHI", "14166 - CHICLAYO");
        // Agrega más correspondencias según sea necesario
    }

    public String getSelectedAgency(String agency) {
        return AGENCIA_MAPPING.get(agency);
    }
}
