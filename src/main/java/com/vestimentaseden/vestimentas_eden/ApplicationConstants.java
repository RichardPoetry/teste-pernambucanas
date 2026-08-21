package com.vestimentaseden.vestimentas_eden;

import java.math.BigDecimal;

public final class ApplicationConstants {

    public static final BigDecimal FRETE = new BigDecimal("20.00");
    public static final BigDecimal VALOR_MINIMO_FRETE_GRATIS = new BigDecimal("200.00");
    public static final BigDecimal DESCONTO_CLIENTE_PLUS = new BigDecimal("0.05").setScale(2);
    public static final BigDecimal DESCONTO_CUPOM_DESC10 = new BigDecimal("0.10").setScale(2);
}