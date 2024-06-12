package com.example.QuanLySach.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @NotNull(message = "ID không được để trống")
    @Min(value = 1, message = "ID phải là số nguyên dương lớn hơn 0")
    private int id;

    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(max = 100, message = "Tiêu đề không được vượt quá 100 ký tự")
    private String tieude;

    @NotBlank(message = "Tác giả không được để trống")
    @Size(max = 100, message = "Tác giả không được vượt quá 100 ký tự")
    private String tentg;

    private String image;
    @NotNull(message = "Giá không được để trống")
    @Min(value = 0, message = "Giá phải là một số không âm")
    private Double price;

    @NotBlank(message = "Thể loại không được để trống")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Thể loại chỉ chấp nhận chữ cái và khoảng trống")
    private String category;
}
