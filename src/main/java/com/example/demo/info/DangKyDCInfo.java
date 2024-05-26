package com.example.demo.info;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DangKyDCInfo {
    Integer id;
    @NotBlank(message = "Không được để trống địa chỉ")
    String diachi;
    @NotBlank(message = "Không được để trống Tỉnh/Thành phố")
    String tinhthanhpho;
    String sdtnguoinhan;
    String hotennguoinhan;
    Timestamp lancapnhatcuoi;
    String nguoitao;
    String nguoicapnhat;
}
