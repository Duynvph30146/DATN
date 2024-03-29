package com.example.demo.info;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonCustom {
    @NotBlank(message = "Vui lòng không để trống!!")
    String key;
    Date tu;
    Date den;
    @NotNull(message = "Vui lòng chọn loại hóa đơn!!")
    Boolean loaiHD;
}
