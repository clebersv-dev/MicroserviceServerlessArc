/* 
 * Arquivo 		  : ObjetoUtil.java
 * Pacote		  : riachuelo.aut.pdvr.integracao.ws.util
 * Copyright (c) 2020, Riachuelo and/or its affiliates. All rights reserved.
 * RCHLO PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.com.impacta.fullstack.user.util;

import java.util.Collection;
import java.util.Optional;

/**
 * <p>Nome: ObjetoUtil.java</p>
 * <p>Propósito: Classe responsável por implementar ...</p>
 *
 * @author ? 
 * @version 1.0
 * @see Referências Externas.
 */
public final class ObjetoUtil {

    /**
     * Verifica se um objeto é vazio.
     *
     * @param <T> the generic type
     * @param obj ...
     * @return optional
     */
    public static <T> Optional<T> verifica(T obj) {
        if (obj == null)
            return Optional.empty();
        if (obj instanceof Collection)
            return ((Collection<?>) obj).size() == 0 ? Optional.empty() : Optional.of(obj);

        final String s = String.valueOf(obj).trim();

        return s.length() == 0 || s.equalsIgnoreCase("null") ? Optional.empty() : Optional.of(obj);
    }

}