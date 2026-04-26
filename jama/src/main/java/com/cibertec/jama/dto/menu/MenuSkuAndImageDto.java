package com.cibertec.jama.dto.menu;

import com.cibertec.jama.entities.menu.MenuImage;
import com.cibertec.jama.entities.menu.MenuSku;

public record MenuSkuAndImageDto(
        MenuSku menuSku,
        MenuImage menuImage
) {
}
