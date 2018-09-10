package com.isscroberto.powernap.data

enum class NapType(val value: Int) {
    NAP_TYPE_POWER(1),
    NAP_TYPE_REFRESH(2),
    NAP_TYPE_RECHARGE(3),
    NAP_TYPE_COFFEE(4),
    NULL(0);

    companion object {
        private val map = NapType.values().associateBy(NapType::value);
        fun fromInt(type: Int) = map[type]
    }
}