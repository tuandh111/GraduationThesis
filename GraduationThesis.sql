use GraduationThesis
INSERT INTO medicine_category (description, name,is_deleted) VALUES
('giảm đau và chống viêm', 'Giảm đau và chống viêm',false),
('gây tê', 'Gây tê',false),
('kháng sinh', 'Kháng sinh',false),
('chống nấm', 'Chống nấm',false),
('chống virus', 'Chống virus',false),
('chống loét miệng', 'Chống loét miệng',false),
('khô miệng', 'Khô miệng',false),
('bệnh nha chu', 'Bệnh nha chu',false),
('chống dị ứng', 'Chống dị ứng',false),
('chống lo âu và an thần', 'Chống lo âu và an thần',false),
('chống viêm', 'Chống viêm',false),
('khử trùng', 'Khử trùng',false),
('hỗ trợ tiêu hóa', 'Hỗ trợ tiêu hóa',false);

INSERT into medicines_dosage_amount(amount,is_deleted) VALUES (1,false),(2,false),(3,false),(4,false),(5,false);

INSERT INTO distribution_medicines (address, contact_person, distribution_name, email, name, note, tax_code,is_deleted) VALUES
('123 Chiến Thắng', 'Nguyen Van A', 'Pharmacy Distribution Inc.', 'contact@pharmacydist.com', 'Pharmacy Distribution Inc.', 'Thuốc tốt', '1234567890',false),
('456 Thành Công', 'Tran Thi B', 'Health Supplies Ltd.', 'support@healthsupplies.com', 'Health Supplies Ltd.', 'Tốt thuốc', '0987654321',false),
('789 Phú Lê', 'Le Van C', 'MedEquip Co.', 'info@medequip.com', 'MedEquip Co.', 'Toa thuốc', '1122334455',false),
('321 Skyler', 'Pham Thi D', 'Wellness Pharmaceuticals', 'sales@wellnesspharma.com', 'Thuốc than', 'Best prices on pharmaceuticals', '5566778899',false),
('654 Khói', 'Hoang Van E', 'Dental Distributors', 'service@dentaldist.com', 'Dental Distributors', 'Đừng', '6677889900',false);

insert into medicines_dosage_unit (unit,is_deleted) VALUES ('Viên',false),('Gói',false),('mg',false),('ml',false);

INSERT into frequency (times_of_day,is_deleted) VALUES ('Sáng',false),('Chiều',false),('Tối',false);

DELIMITER //

DROP PROCEDURE IF EXISTS InsertTreatmentDuration//

CREATE PROCEDURE InsertTreatmentDuration()
BEGIN
    DECLARE counter INT DEFAULT 1;
    WHILE counter <= 180 DO
        INSERT INTO treatment_duration (Quantity,is_deleted) VALUES (counter,false);
        SET counter = counter + 1;
    END WHILE;
END//

DELIMITER ;

CALL InsertTreatmentDuration();

INSERT INTO medicines (before_eating, medicine_name, distribution_medicines_id, medicine_category_id, medicines_dosage_amount_id, medicines_dosage_unit_id,is_deleted) VALUES
(True, 'Ibuprofen', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='giảm đau và chống viêm'), 1, 1,false),
(True, 'Acetaminophen (Paracetamol)', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='giảm đau và chống viêm'), 2, 1,false),
(True, 'Aspirin', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='giảm đau và chống viêm'), 3, 1,false),
(True, 'Naproxen', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='giảm đau và chống viêm'), 4, 1,false),
(True, 'Ketorolac', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='giảm đau và chống viêm'), 5, 1,false),
(True, 'Lidocaine', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='gây tê'), 1, 2,false),
(True, 'Articaine', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='gây tê'), 2, 2,false),
(True, 'Mepivacaine', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='gây tê'), 3, 2,false),
(True, 'Bupivacaine', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='gây tê'), 4, 2,false),
(True, 'Prilocaine', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='gây tê'), 5, 2,false),
(True, 'Amoxicillin', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 1, 3,false),
(True, 'Clindamycin', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 2, 3,false),
(True, 'Metronidazole', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 3, 3,false),
(True, 'Azithromycin', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 4, 3,false),
(True, 'Doxycycline', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 5, 3,false),
(True, 'Nystatin', 4, (SELECT medicine_category_id FROM medicine_category WHERE name='chống nấm'), 1, 4,false),
(True, 'Clotrimazole', 4, (SELECT medicine_category_id FROM medicine_category WHERE name='chống nấm'), 2, 4,false),
(True, 'Fluconazole', 4, (SELECT medicine_category_id FROM medicine_category WHERE name='chống nấm'), 3, 4,false),
(True, 'Acyclovir', 5, (SELECT medicine_category_id FROM medicine_category WHERE name='chống virus'), 1, 1,false),
(True, 'Valacyclovir', 5, (SELECT medicine_category_id FROM medicine_category WHERE name='chống virus'), 2, 1,false),
(True, 'Triamcinolone Acetonide', 4, (SELECT medicine_category_id FROM medicine_category WHERE name='chống loét miệng'), 1, 1,false),
(True, 'Orabase', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='chống loét miệng'), 2, 1,false),
(True, 'Pilocarpine', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='khô miệng'), 1, 1,false),
(True, 'Cevimeline', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='khô miệng'), 2, 1,false),
(True, 'Chlorhexidine Gluconate', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='bệnh nha chu'), 1, 1,false),
(True, 'Doxycycline Gel', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='bệnh nha chu'), 2, 1,false),
(True, 'Diphenhydramine', 4, (SELECT medicine_category_id FROM medicine_category WHERE name='chống dị ứng'), 1, 1,false),
(True, 'Diazepam', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='chống lo âu và an thần'), 1, 1,false),
(True, 'Midazolam', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='chống lo âu và an thần'), 2, 1,false),
(True, 'Nitrous Oxide', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='chống lo âu và an thần'), 3, 1,false),
(True, 'Ropivacaine', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='gây tê'), 5, 1,false),
(True, 'Epinephrine', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='gây tê'), 5, 1,false),
(True, 'Tetracaine', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='gây tê'), 5, 1,false),
(True, 'Oxycodone', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='giảm đau và chống viêm'), 5, 1,false),
(True, 'Hydrocodone', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='giảm đau và chống viêm'), 1, 1,false),
(True, 'Codeine', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='giảm đau và chống viêm'), 1, 1,false),
(True, 'Morphine', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='giảm đau và chống viêm'), 2, 1,false),
(True, 'Prednisone', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='chống viêm'), 1, 1,false),
(True, 'Methylprednisolone', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='chống viêm'), 2, 1,false),
(True, 'Dexamethasone', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='chống viêm'), 3, 1,false),
(True, 'Amoxicillin/Clavulanic Acid', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 4, 1,false),
(True, 'Ciprofloxacin', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 5, 1,false),
(True, 'Levofloxacin', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 5, 1,false),
(True, 'Ceftriaxone', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 5, 1,false),
(True, 'Cephalexin', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 5, 1,false),
(True, 'Metronidazole Gel', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 5, 1,false),
(True, 'Doxycycline Capsule', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 1, 1,false),
(True, 'Clindamycin Gel', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 1, 1,false),
(True, 'Mupirocin', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 2, 1,false),
(True, 'Chlorhexidine Varnish', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='bệnh nha chu'), 3, 1,false),
(True, 'Minocycline', 3, (SELECT medicine_category_id FROM medicine_category WHERE name='kháng sinh'), 4, 1,false),
(True, 'Clotrimazole Troche', 4, (SELECT medicine_category_id FROM medicine_category WHERE name='chống nấm'), 2, 1,false),
(True, 'Nystatin Oral Suspension', 4, (SELECT medicine_category_id FROM medicine_category WHERE name='chống nấm'), 3, 1,false),
(True, 'Fluconazole Tablet', 4, (SELECT medicine_category_id FROM medicine_category WHERE name='chống nấm'), 4, 1,false),
(True, 'Povidone-Iodine', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='khử trùng'), 1, 1,false),
(True, 'Hydrogen Peroxide Mouthwash', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='khử trùng'), 2, 1,false),
(True, 'Dexamethasone Mouthwash', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='chống viêm'), 4, 1,false),
(True, 'Triamcinolone Oral Paste', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='chống viêm'), 5, 1,false),
(True, 'Benzocaine Topical', 2, (SELECT medicine_category_id FROM medicine_category WHERE name='gây tê'), 5, 1,false),
(True, 'Probiotics', 1, (SELECT medicine_category_id FROM medicine_category WHERE name='hỗ trợ tiêu hóa'), 1, 1,false);


INSERT INTO dental_issues (name,is_deleted) VALUES
('Đau răng',false),
('Viêm nướu',false),
('Chảy máu nướu',false),
('Sưng nướu',false),
('Hơi thở có mùi',false),
('Răng nhạy cảm',false),
('Răng bị sứt mẻ',false),
('Răng bị mòn',false),
('Răng lung lay',false),
('Nhiễm trùng răng',false),
('Áp xe răng',false),
('Mòn men răng',false),
('Răng mọc lệch',false),
('Răng khôn mọc lệch',false),
('Sâu răng',false),
('Loét miệng',false),
('Khô miệng',false),
('Viêm tủy răng',false),
('Răng ngả màu',false),
('Nhiệt miệng',false),
('Viêm quanh răng',false),
('Răng ê buốt',false),
('Lở loét nướu',false),
('Nướu thâm đen',false),
('Răng thưa',false),
('Răng lệch lạc',false),
('Răng cửa bị hư hại',false),
('Mất răng',false),
('Răng mọc thêm',false),
('Đau quai hàm',false),
('Nướu co rút',false),
('Răng hô',false),
('Răng móm',false),
('Hàm không cân xứng',false),
('Răng chèn ép',false),
('Sâu ngầm',false),
('Vôi răng',false),
('Viêm lợi do mảng bám',false),
('Mọc mụn nhọt trong miệng',false),
('Vết cắt trong miệng',false),
('Nhiễm trùng nướu',false),
('Lệch đường giữa',false),
('Đau răng khi cắn',false),
('Răng không đều màu',false),
('Sứt mẻ răng cửa',false),
('Hàm răng không khớp',false),
('Thẩm mỹ',false),
('Mất răng hàng loạt',false),
('Viêm lợi do thuốc',false),
('Nhiễm trùng chân răng',false),
('Răng mọc quá nhiều',false),
('Mất răng cửa',false),
('Thay đổi màu nướu',false),
('Tiêu xương hàm',false),
('Răng bị đen',false),
('Sưng nướu quanh răng khôn',false),
('Nướu bị lở loét',false),
('Vết thương trong miệng do dụng cụ chỉnh nha',false),
('Nứt nẻ môi do thời tiết',false),
('Nhiễm trùng chân răng sau phẫu thuật',false);

INSERT INTO treatment (treatment_name,is_deleted) VALUES
('Điều trị tủy',false),
('Trám răng',false),
('Nhổ răng',false),
('Súc miệng bằng nước muối',false),
('Vệ sinh răng miệng',false),
('Sử dụng thuốc kháng sinh',false),
('Đánh răng nhẹ nhàng',false),
('Dùng chỉ nha khoa đúng cách',false),
('Súc miệng bằng dung dịch kháng khuẩn',false),
('Sử dụng thuốc kháng viêm',false),
('Điều trị nha chu',false),
('Vệ sinh răng miệng kỹ lưỡng',false),
('Uống nhiều nước',false),
('Sử dụng nước súc miệng kháng khuẩn',false),
('Dùng kem đánh răng cho răng nhạy cảm',false),
('Tránh thực phẩm quá nóng hoặc lạnh',false),
('Bọc răng sứ',false),
('Sử dụng bảo vệ răng khi nghiến răng',false),
('Điều chỉnh chế độ ăn uống',false),
('Nẹp răng',false),
('Dẫn lưu mủ',false),
('Dùng kem đánh răng chứa fluoride',false),
('Niềng răng',false),
('Chỉnh nha',false),
('Nhổ răng khôn',false),
('Sử dụng thuốc bôi loét miệng',false),
('Sử dụng thuốc kích thích tiết nước bọt',false),
('Sử dụng thuốc giảm đau và kháng viêm',false),
('Tẩy trắng răng',false),
('Sử dụng miếng dán tẩy trắng răng',false),
('Sử dụng thuốc bôi giảm đau',false),
('Khám nha sĩ để xác định nguyên nhân và điều trị phù hợp',false),
('Làm cầu răng',false),
('Nhổ răng mọc thêm',false),
('Điều trị nha khoa phù hợp',false),
('Ghép nướu',false),
('Phẫu thuật chỉnh hình',false),
('Phẫu thuật chỉnh hình hàm',false),
('Lấy cao răng',false),
('Vệ sinh răng miệng định kỳ',false),
('Sử dụng chỉ nha khoa',false),
('Vệ sinh vùng miệng sạch sẽ',false),
('Rửa vết thương bằng nước muối',false),
('Vệ sinh răng miệng tốt',false),
('Sử dụng veneer',false),
('Tư vấn và điều trị thẩm mỹ nha khoa',false),
('Sử dụng răng giả',false),
('Cấy ghép răng',false),
('Thay đổi thuốc hoặc điều chỉnh liều lượng dưới sự giám sát của bác sĩ',false),
('Ghép xương',false),
('Sử dụng sáp bảo vệ',false),
('Sử dụng son dưỡng môi',false),
('Giữ ẩm cho môi',false);


INSERT INTO service_type (type_name,is_deleted) VALUES
('Tổng quát',false),
('Chụp phim',false),
('Phòng ngừa',false),
('Chữa tủy',false),
('trám răng',false),
('Nhổ răng',false),
('Tẩy trắng răng',false),
('Chốt, cùi giả',false),
('Phục hồi tháo lắp',false),
('Răng sứ',false),
('Implant',false),
('chỉnh nha',false),
('Thẩm mỹ',false),
('Tiểu phẫu',false),
('Phục hồi cố định',false),
('Chữa trị bệnh lý miệng',false);

INSERT INTO service (service_name, price, time_estimate, service_type_id,is_deleted)
VALUES
('Khám tổng quát', 0, 25, 1,false),
('X quang kỹ thuật số (1 phim)', 20000, 15, 2,false),
('X quang toàn cảnh', 100000, 15, 2,false),
('X quang sọ nghiêng', 100000, 15, 2,false),
('X quang sọ thẳng', 100000, 15, 2,false),
('X quang CT răng hàm mặt - 2 hàm', 600000, 15, 2,false),
('X quang CT răng hàm mặt - 1 hàm', 500000, 15, 2,false),
('Cạo vôi răng', 250000, 20, 3,false),
('Trám bít hố rãnh', 200000, 20, 3,false),
('Trám răng bằng Composite', 150000, 35, 4,false),
('Trám răng thẩm mỹ bằng Composite', 400000, 35, 4,false),
('Chữa tủy', 800000, 35, 4,false),
('Nạo túi nha chu', 200000, 35, 4,false),
('Nhổ răng sữa trẻ em', 50000, 15, 5,false),
('Nhổ răng vĩnh viễn mọc thẳng', 400000, 20, 5,false),
('Nhổ răng khôn hàm dưới', 1500000, 45, 5,false),
('Nhổ răng khôn hàm trên', 800000, 45, 5,false),
('Tẩy trắng răng tại phòng khám', 1500000, 40, 6,false),
('Tẩy trắng răng tại nhà', 1000000, 40, 6,false),
('Tẩy trắng răng kết hợp', 2200000, 40, 6,false),
('Tẩy trắng nội nha', 500000, 40, 6,false),
('Chốt, cùi giả', 1000000, 40, 7,false),
('Răng sứ tháo lắp', 800000, 30, 8,false),
('Răng sứ cao cấp tháo lắp', 1000000, 30, 8,false),
('Răng tạm', 200000, 30, 8,false),
('Hàm khung kim loại', 1000000, 30, 8,false),
('Sứ Kim loại', 1500000, 30, 9,false),
('Sứ Zirconia', 3000000, 30, 9,false),
('Mặt dán sứ', 7000000, 30, 9,false),
('Implant trọn gói', 25000000, 45, 10,false),
('Ghép xương', 7000000, 45, 11,false),
('Chỉnh nha', 20000000, 45, 11,false),
('Đính đá', 400000, 35, 12,false),
('Đính đá trên răng sứ', 600000, 35, 12,false),
('Mặt phẳng nghiêng', 2000000, 45, 11,false),
('Cắt chóp, nạo nang, tiểu phẫu', 2000000, 45, 13,false),
('Cắt nướu làm dài thân răng', 500000, 45, 13,false),
('Làm hàm giả tháo lắp', 5000000, 30, 8,false),
('Làm hàm giả cố định trên Implant', 20000000, 45, 14,false),
('Chăm sóc và bảo dưỡng răng Implant', 500000, 30, 14,false),
('Phẫu thuật tạo hình xương ổ răng', 10000000, 45, 13,false),
('Tư vấn và thiết kế nụ cười', 500000, 30, 15,false),
('Điều trị lở loét miệng', 200000, 20, 16,false),
('Điều trị khô miệng', 500000, 20, 16,false),
('Điều trị răng nhiễm màu kháng sinh', 3000000, 30, 15,false),
('Điều trị viêm lợi do mảng bám', 500000, 20, 16,false),
('Điều trị viêm lợi do thuốc', 500000, 20, 16,false),
('Phẫu thuật cắt lợi', 5000000, 45, 13,false),
('Điều trị bệnh lý nướu', 1500000, 30, 16,false),
('Tạo hình lợi thẩm mỹ', 5000000, 30, 15,false),
('Implant ( ức) trọn gói (Implant + Abutment Zirconia + mão sứ Zirconia)', 33000000, 45, 10,false),
('Nhịp răng sứ Titan trên Implant', 3500000, 45, 10,false),
('Nhịp răng sứ Zirconia trên Implant', 5500000, 45, 10,false),
('Ghép xương (xương USA) tùy trường hợp từ', 70000000, 45, 10,false),
('Chỉnh nha, implant', 120000000, 45, 11,false);

INSERT INTO distribution_supplies (address, contact_person, distribution, email, name, note, tax_code,is_deleted)
VALUES
('123 Thanh xuân', 'Lê Hoàn', 'ABC Dental Supplies', 'john@example.com',  'Distribution Center A', 'Main distribution center', '1234567890',false),
('456 Vĩnh Thuận', 'Jane Smith', 'Dental Supply Co.', 'jane@example.com',  'Distribution Center B', 'Secondary distribution center', '0987654321',false),
('789 Cây xon', 'Michael Johnson', 'Healthy Smiles Inc.', 'michael@example.com',  'Distribution Center C', 'Additional distribution center', '1357924680',false);

INSERT INTO dental_supplies (supplies_name,  distribution_id,is_deleted)
VALUES
('Khâu',  1,false),
('Mắc cài',  1,false),
('Dây cung', 1,false),
('Chỉ nha khoa',  1,false),
('Gương nha khoa',  1,false),
('Kềm nhổ răng',  1,false),
('Bàn chải nha khoa',  1,false),
('Cạo vôi răng',  1,false),
('Cần chỉnh nha',  1,false),
('Đèn chiếu quang',  1,false),
('Máy xquang nha khoa',  1,false),
('Khí cụ mở miệng',  1,false),
('Dao phẫu thuật',  1,false),
('Dụng cụ nạo túi nha chu',  1,false),
('Bơm tiêm nha khoa',  1,false),
('Găng tay y tế',  1,false),
('Máy siêu âm',  1,false),
('Dụng cụ thử tủy',  1,false),
('Máy trám răng',  1,false),
('Đũa hút nước bọt',  1,false),
('Dụng cụ nén bông',  1,false),
('Mỏ neo răng',  1,false),
('Dụng cụ đo chĐiều sâu túi nha chu',  1,false),
('Chất hàn răng',  1,false),
('Máy mài răng',  1,false),
('Khay lấy dấu răng',  1,false),
('Chất lấy dấu răng',  1,false),
('Máy chiếu phim X-quang',  1,false),
('Nút nha khoa',  1,false),
('Kềm cắt dây',  1,false),
('Kềm kẹp máu',  1,false),
('Dụng cụ định hình tủy răng',  1,false),
('Găng tay cao su',  1,false),
('Kính bảo hộ',  1,false),
('Nha cụ phân tách',  1,false),
('Kềm kẹp gắp dụng cụ',  1,false),
('Khay dụng cụ',  1,false),
('Chất làm tê tại chỗ',  1,false),
('Đầu đèn LED',  1,false),
('Máy hút dịch nha khoa',  1,false),
('Dụng cụ đo lường răng',  1,false),
('Dụng cụ lấy tủy răng',  1,false),
('Chất làm đầy răng tạm thời',  1,false),
('Máy đo độ nhạy cảm răng', 1,false),
('Dụng cụ nạo sâu răng',  1,false),
('Dụng cụ kiểm tra khớp cắn',  1,false),
('Dụng cụ nén amalgam',  1,false),
('Kẹp nha khoa',  1,false),
('Mũi khoan nha khoa',  1,false),
('Băng vệ sinh miệng',  1,false),
('Máy đo độ pH miệng',  1,false),
('Kéo nha khoa',  1,false),
('Dụng cụ lấy cao răng siêu âm',  1,false),
('Dụng cụ lấy cao răng thủ công',  1,false),
('Chất bảo vệ men răng',  1,false),
('Đèn trám răng',  1,false),
('Dụng cụ chỉnh hình miệng',  1,false),
('Kẹp dây cung',  1,false),
('Bộ dụng cụ chỉnh hình',  1,false),
('Chất làm sạch ống tủy',  1,false);


INSERT INTO abnormality (name, is_deleted)
VALUES
('Viêm nướu', false),
('Viêm túi nha chu', false),
('Mất răng', false),
('Áp xe răng', false),
('Viêm tủy răng', false),
('Sứt mẻ', false),
('Gãy răng', false),
('Trám răng hỏng', false),
('Mất xương', false),
('Tiêu xương', false),
('Mòn men răng', false),
('Răng mọc lệch', false),
('Núm phụ', false),
('Răng nằm ngược', false),
('Cột đen trên răng', false),
('Tủy răng bị chết', false),
('Răng lõm hoặc lồi', false),
('Nang chân răng', false),
('Răng bị thâm đen', false),
('Vùng xâm nhập của mảng bám', false);

INSERT INTO imaging_planes (name, is_deleted)
VALUES
('Hướng', false),
('Trước', false),
('Sau', false),
('Trên', false),
('Dưới', false),
('Bên trái', false),
('Bên phải', false);

INSERT INTO medical_history (name,is_deleted) VALUES
('Cảm lạnh',false),
('Đau đầu',false),
('Tiểu đường',false),
('Huyết áp cao',false),
('Đau dạ dày',false),
('Trào ngược dạ dày',false),
('Viêm phế quản',false),
('Viêm họng',false),
('Viêm amidan',false),
('Viêm phổi',false),
('Viêm gan',false),
('Viêm mật',false),
('Viêm thận',false),
('Viêm bàng quang',false),
('Viêm ruột thừa',false),
('Viêm ruột kết',false),
('Viêm ruột già',false),
('Viêm nhiễm đường tiểu',false),
('Viêm nhiễm đường ruột',false),
('Tiêu chảy',false),
('Táo bón',false),
('Chứng suyễn',false),
('Hen suyễn',false),
('Viêm khớp',false),
('Đau xương khớp',false),
('Viêm xương',false),
('Cứng cổ',false),
('Đau vai gáy',false),
('Đau lưng',false),
('Đau dây thần kinh tọa',false),
('Đau đốt sống',false),
('Cột sống trụy',false),
('Cao huyết áp',false),
('Thấp huyết áp',false),
('Thiếu máu não',false),
('Đau tim',false),
('Mất trí nhớ',false),
('Trầm cảm',false),
('Lo âu',false),
('Suy giảm trí tuệ',false),
('Mất ngủ',false),
('Rối loạn tiền đình',false),
('Đau mắt',false),
('Viêm mắt',false),
('Viêm tai giữa',false),
('Viêm tai ngoài',false),
('Đau răng',false),
('Viêm nướu',false),
('Viêm amidan',false),
('Viêm tai giữa',false),
('Viêm xoang',false),
('Viêm tinh hoàn',false),
('Viêm tuyến tiền liệt',false),
('Viêm tuyến vú',false),
('U xơ tử cung',false),
('U nang buồng trứng',false),
('U xơ vú',false),
('U gan',false),
('U gan ác tính',false),
('U dạ dày',false),
('U ruột',false),
('U thận',false),
('U tiền liệt tuyến',false),
('U não',false),
('U phổi',false),
('U vú',false),
('U hậu môn',false),
('U tiểu đường',false),
('U gan cấp tính',false),
('U phế quản',false),
('U tuyến nước bọt',false),
('U tiền liệt',false),
('U da',false),
('U mạch máu não',false),
('U trung liên',false),
('U thanh quản',false),
('U gan tái phát',false),
('U da non',false),
('U tiền liệt ác tính',false),
('U tuyến nước bọt ác tính',false),
('U đường tiêu hóa',false),
('U tuyến nước bọt cấp tính',false),
('U tụy',false),
('U dạ dày cấp tính',false),
('U hậu môn ác tính',false),
('U tuyến nước bọt cấp tính',false),
('U tử cung',false),
('U thần kinh ngoại biên',false),
('U da ác tính',false),
('U bàng quang',false),
('U phổi ác tính',false);

insert into department (department_name,is_deleted) VALUES ('Lễ tân',false),('Kỹ thuật viên chẩn đoán hình ảnh',false),('Y tá',false);

INSERT INTO specialty (specialty_name,is_deleted) VALUES
('Nha khoa tổng quát',false),
('Chỉnh nha',false),
('Chữa trị viêm nướu',false),
('Chăm sóc răng miệng trẻ em',false),
('Phục hình răng sứ',false),
('Thẩm mỹ nha khoa',false),
('Nha khoa phòng ngừa',false),
('Chỉnh nha trẻ em',false),
('Chữa trị nhiễm trùng răng miệng',false),
('Phục hình nha khoa',false);

insert into appointment_type (type_name,is_deleted) VALUES ('Khám chữa bệnh',false),('Tư vấn chỉnh nha',false),('Tiểu phẫu',false);


INSERT INTO issues_treatment_automation (dental_issues_id, treatment_id) VALUES
-- Đau răng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Đau răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Đau răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Đau răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nhổ răng')),

-- Viêm nướu
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Viêm nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Súc miệng bằng nước muối')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Viêm nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Viêm nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng sinh')),

-- Chảy máu nướu
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Chảy máu nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Đánh răng nhẹ nhàng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Chảy máu nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Dùng chỉ nha khoa đúng cách')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Chảy máu nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Súc miệng bằng dung dịch kháng khuẩn')),

-- Sưng nướu
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sưng nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng viêm')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sưng nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sưng nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị nha chu')),

-- Hơi thở có mùi
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Hơi thở có mùi'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng kỹ lưỡng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Hơi thở có mùi'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Uống nhiều nước')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Hơi thở có mùi'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng nước súc miệng kháng khuẩn')),

-- Răng nhạy cảm
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng nhạy cảm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Dùng kem đánh răng cho răng nhạy cảm')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng nhạy cảm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Tránh thực phẩm quá nóng hoặc lạnh')),

-- Răng bị sứt mẻ
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng bị sứt mẻ'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng bị sứt mẻ'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Bọc răng sứ')),

-- Răng bị mòn
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng bị mòn'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng bảo vệ răng khi nghiến răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng bị mòn'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều chỉnh chế độ ăn uống')),

-- Răng lung lay
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng lung lay'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị nha chu')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng lung lay'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nẹp răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng lung lay'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nhổ răng')),

-- Nhiễm trùng răng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nhiễm trùng răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng sinh')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nhiễm trùng răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),

-- Áp xe răng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Áp xe răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng sinh')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Áp xe răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Dẫn lưu mủ')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Áp xe răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Áp xe răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'nhổ răng')),
-- Mòn men răng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mòn men răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Dùng kem đánh răng chứa fluoride')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mòn men răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều chỉnh chế độ ăn uống')),

-- Răng mọc lệch
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng mọc lệch'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Niềng răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng mọc lệch'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Chỉnh nha')),

-- Răng khôn mọc lệch
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng khôn mọc lệch'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nhổ răng khôn')),

-- Sâu răng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sâu răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sâu răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),

-- Loét miệng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Loét miệng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Súc miệng bằng nước muối')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Loét miệng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc bôi loét miệng')),

-- Khô miệng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Khô miệng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Uống nhiều nước')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Khô miệng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kích thích tiết nước bọt')),

-- Viêm tủy răng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Viêm tủy răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Viêm tủy răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc giảm đau và kháng viêm')),

-- Răng ngả màu
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng ngả màu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Tẩy trắng răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng ngả màu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng miếng dán tẩy trắng răng')),

-- Nhiệt miệng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nhiệt miệng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Súc miệng bằng nước muối')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nhiệt miệng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc bôi giảm đau')),

-- Viêm quanh răng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Viêm quanh răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Súc miệng bằng dung dịch kháng khuẩn')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Viêm quanh răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị nha chu')),

-- Răng ê buốt
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng ê buốt'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Dùng kem đánh răng cho răng nhạy cảm')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng ê buốt'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Tránh thực phẩm quá nóng hoặc lạnh')),

-- Lở loét nướu
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Lở loét nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng viêm')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Lở loét nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Súc miệng bằng nước muối')),

-- Nướu thâm đen
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nướu thâm đen'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Khám nha sĩ để xác định nguyên nhân và điều trị phù hợp')),

-- Răng thưa
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng thưa'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Chỉnh nha')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng thưa'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Niềng răng')),

-- Răng lệch lạc
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng lệch lạc'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Chỉnh nha')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng lệch lạc'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Niềng răng')),

-- Răng cửa bị hư hại
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng cửa bị hư hại'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng cửa bị hư hại'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Bọc răng sứ')),

-- Mất răng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mất răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Cấy ghép răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mất răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Làm cầu răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mất răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng răng giả')),

-- Răng mọc thêm
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng mọc thêm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nhổ răng mọc thêm')),

-- Đau quai hàm
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Đau quai hàm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng bảo vệ răng khi nghiến răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Đau quai hàm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị nha khoa phù hợp')),

-- Nướu co rút
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nướu co rút'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Ghép nướu')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nướu co rút'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng tốt')),

-- Răng hô
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng hô'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Niềng răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng hô'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Phẫu thuật chỉnh hình')),

-- Răng móm
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng móm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Niềng răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng móm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Phẫu thuật chỉnh hình')),

-- Hàm không cân xứng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Hàm không cân xứng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Phẫu thuật chỉnh hình hàm')),

-- Răng chèn ép
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng chèn ép'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nhổ răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng chèn ép'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Chỉnh nha')),

-- Sâu ngầm
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sâu ngầm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sâu ngầm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),

-- Vôi răng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Vôi răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Lấy cao răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Vôi răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng định kỳ')),

-- Viêm lợi do mảng bám
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Viêm lợi do mảng bám'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng chỉ nha khoa')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Viêm lợi do mảng bám'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng kỹ lưỡng')),

-- Mọc mụn nhọt trong miệng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mọc mụn nhọt trong miệng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng viêm')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mọc mụn nhọt trong miệng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh vùng miệng sạch sẽ')),

-- Vết cắt trong miệng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Vết cắt trong miệng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Rửa vết thương bằng nước muối')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Vết cắt trong miệng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng viêm')),

-- Nhiễm trùng nướu
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nhiễm trùng nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng sinh')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nhiễm trùng nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng kỹ lưỡng')),

-- Lệch đường giữa
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Lệch đường giữa'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Chỉnh nha')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Lệch đường giữa'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Niềng răng')),

-- Đau răng khi cắn
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Đau răng khi cắn'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng bảo vệ răng khi nghiến răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Đau răng khi cắn'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),

-- Răng không đều màu
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng không đều màu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Tẩy trắng răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng không đều màu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng veneer')),

-- Sứt mẻ răng cửa
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sứt mẻ răng cửa'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sứt mẻ răng cửa'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Bọc răng sứ')),

-- Hàm răng không khớp
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Hàm răng không khớp'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Chỉnh nha')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Hàm răng không khớp'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Niềng răng')),

-- Thẩm mỹ
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Thẩm mỹ'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Tư vấn và điều trị thẩm mỹ nha khoa')),

-- Mất răng hàng loạt
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mất răng hàng loạt'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng răng giả')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mất răng hàng loạt'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Cấy ghép răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mất răng hàng loạt'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Làm cầu răng')),

-- Viêm lợi do thuốc
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Viêm lợi do thuốc'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Thay đổi thuốc hoặc điều chỉnh liều lượng dưới sự giám sát của bác sĩ')),

-- Nhiễm trùng chân răng
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nhiễm trùng chân răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nhiễm trùng chân răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng sinh')),

-- Răng mọc quá nhiều
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng mọc quá nhiều'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nhổ răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng mọc quá nhiều'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Chỉnh nha')),

-- Mất răng cửa
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mất răng cửa'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mất răng cửa'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Bọc răng sứ')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Mất răng cửa'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Cấy ghép răng')),

-- Thay đổi màu nướu
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Thay đổi màu nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Khám nha sĩ để xác định nguyên nhân và điều trị phù hợp')),

-- Tiêu xương hàm
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Tiêu xương hàm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Ghép xương')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Tiêu xương hàm'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Cấy ghép răng')),

-- Răng bị đen
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng bị đen'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Tẩy trắng răng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Răng bị đen'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy răng')),

-- Sưng nướu quanh răng khôn
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sưng nướu quanh răng khôn'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Sưng nướu quanh răng khôn'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng viêm')),

-- Nướu bị lở loét
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nướu bị lở loét'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Súc miệng bằng nước muối')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nướu bị lở loét'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc bôi giảm đau')),

-- Vết thương trong miệng do dụng cụ chỉnh nha
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Vết thương trong miệng do dụng cụ chỉnh nha'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng kỹ lưỡng')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Vết thương trong miệng do dụng cụ chỉnh nha'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng sáp bảo vệ')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Vết thương trong miệng do dụng cụ chỉnh nha'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Khám nha sĩ để xác định nguyên nhân và điều trị phù hợp')),

-- Nứt nẻ môi do thời tiết
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nứt nẻ môi do thời tiết'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng son dưỡng môi')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nứt nẻ môi do thời tiết'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Giữ ẩm cho môi')),

-- Nhiễm trùng chân răng sau phẫu thuật
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nhiễm trùng chân răng sau phẫu thuật'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng sinh')),
((SELECT dental_issues_id FROM dental_issues WHERE name = 'Nhiễm trùng chân răng sau phẫu thuật'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng'))
;

INSERT INTO service_treatment (is_deleted, service_id, treatment_id)
VALUES
-- Điều trị tủy, trám răng: Chữa tủy, Trám răng bằng Composite
(false, (SELECT service_id FROM service WHERE service_name = 'Chữa tủy'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),
(false, (SELECT service_id FROM service WHERE service_name = 'Trám răng bằng Composite'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),

-- Súc miệng bằng nước muối, vệ sinh răng miệng, sử dụng thuốc kháng sinh: Cạo vôi răng, Nạo túi nha chu, Điều trị viêm lợi do thuốc
(false, (SELECT service_id FROM service WHERE service_name = 'Cạo vôi răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Súc miệng bằng nước muối')),
(false, (SELECT service_id FROM service WHERE service_name = 'Nạo túi nha chu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Điều trị viêm lợi do thuốc'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng sinh')),

-- Đánh răng nhẹ nhàng, dùng chỉ nha khoa đúng cách, súc miệng bằng dung dịch kháng khuẩn: Nạo túi nha chu, Điều trị bệnh lý nướu
(false, (SELECT service_id FROM service WHERE service_name = 'Nạo túi nha chu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Đánh răng nhẹ nhàng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Điều trị bệnh lý nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Dùng chỉ nha khoa đúng cách')),
(false, (SELECT service_id FROM service WHERE service_name = 'Điều trị bệnh lý nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Súc miệng bằng dung dịch kháng khuẩn')),

-- Sử dụng thuốc kháng viêm, vệ sinh răng miệng, điều trị nha chu: Nạo túi nha chu, Điều trị bệnh lý nướu
(false, (SELECT service_id FROM service WHERE service_name = 'Nạo túi nha chu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng viêm')),
(false, (SELECT service_id FROM service WHERE service_name = 'Điều trị bệnh lý nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Điều trị bệnh lý nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị nha chu')),

-- Vệ sinh răng miệng kỹ lưỡng, uống nhiều nước, sử dụng nước súc miệng kháng khuẩn: Cạo vôi răng, Điều trị răng nhiễm màu kháng sinh
(false, (SELECT service_id FROM service WHERE service_name = 'Cạo vôi răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng kỹ lưỡng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Điều trị răng nhiễm màu kháng sinh'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Uống nhiều nước')),
(false, (SELECT service_id FROM service WHERE service_name = 'Điều trị răng nhiễm màu kháng sinh'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng nước súc miệng kháng khuẩn')),

-- Dùng kem đánh răng cho răng nhạy cảm, tránh thực phẩm quá nóng hoặc lạnh: Trám răng bằng Composite, Trám răng thẩm mỹ bằng Composite
(false, (SELECT service_id FROM service WHERE service_name = 'Trám răng bằng Composite'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Dùng kem đánh răng cho răng nhạy cảm')),
(false, (SELECT service_id FROM service WHERE service_name = 'Trám răng thẩm mỹ bằng Composite'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Tránh thực phẩm quá nóng hoặc lạnh')),

-- Trám răng hoặc bọc răng: Trám bít hố rãnh, Trám răng bằng Composite
(false, (SELECT service_id FROM service WHERE service_name = 'Trám bít hố rãnh'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Trám răng bằng Composite'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Bọc răng sứ')),

-- Sử dụng bảo vệ răng khi nghiến răng, điều chỉnh chế độ ăn uống: Sứ Zirconia, Trám răng bằng Composite
(false, (SELECT service_id FROM service WHERE service_name = 'Sứ Zirconia'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng bảo vệ răng khi nghiến răng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Trám răng bằng Composite'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều chỉnh chế độ ăn uống')),

-- Điều trị nha chu, nẹp răng, nhổ răng nếu cần: Nhổ răng sữa trẻ em, Nhổ răng vĩnh viễn mọc thẳng, Nhổ răng khôn hàm dưới
(false, (SELECT service_id FROM service WHERE service_name = 'Nhổ răng sữa trẻ em'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị nha chu')),
(false, (SELECT service_id FROM service WHERE service_name = 'Nhổ răng vĩnh viễn mọc thẳng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nẹp răng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Nhổ răng khôn hàm dưới'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nhổ răng')),

-- Sử dụng thuốc kháng sinh, điều trị tủy răng: Chữa tủy, Trám răng bằng Composite, Trám răng thẩm mỹ bằng Composite
(false, (SELECT service_id FROM service WHERE service_name = 'Chữa tủy'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng sinh')),
(false, (SELECT service_id FROM service WHERE service_name = 'Trám răng bằng Composite'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),
(false, (SELECT service_id FROM service WHERE service_name = 'Trám răng thẩm mỹ bằng Composite'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),

-- Sử dụng thuốc kháng sinh, dẫn lưu mủ, điều trị tủy hoặc nhổ răng: Chữa tủy, Nạo túi nha chu, Nhổ răng khôn
(false, (SELECT service_id FROM service WHERE service_name = 'Chữa tủy'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Sử dụng thuốc kháng sinh')),
(false, (SELECT service_id FROM service WHERE service_name = 'Nạo túi nha chu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Dẫn lưu mủ')),
(false, (SELECT service_id FROM service WHERE service_name = 'Nhổ răng khôn hàm dưới'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều trị tủy')),
(false, (SELECT service_id FROM service WHERE service_name = 'Nhổ răng khôn hàm dưới'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nhổ răng')),

-- Sử dụng kem đánh răng chứa fluoride, điều chỉnh chế độ ăn uống: Cạo vôi răng, Điều trị bệnh lý nướu
(false, (SELECT service_id FROM service WHERE service_name = 'Cạo vôi răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Dùng kem đánh răng chứa fluoride')),
(false, (SELECT service_id FROM service WHERE service_name = 'Điều trị bệnh lý nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Điều chỉnh chế độ ăn uống')),

-- Niềng răng, chỉnh nha: Niềng răng, Chỉnh nha
(false, (SELECT service_id FROM service WHERE service_name = 'Niềng răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Niềng răng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Chỉnh nha'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Chỉnh nha')),

-- Nhổ răng khôn: Nhổ răng khôn hàm dưới, Nhổ răng khôn hàm trên
(false, (SELECT service_id FROM service WHERE service_name = 'Nhổ răng khôn hàm dưới'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nhổ răng khôn')),
(false, (SELECT service_id FROM service WHERE service_name = 'Nhổ răng khôn hàm trên'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Nhổ răng khôn')),

-- Trám răng, bọc răng: Trám răng bằng Composite, Trám răng thẩm mỹ bằng Composite, Bọc răng sứ
(false, (SELECT service_id FROM service WHERE service_name = 'Trám răng bằng Composite'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Trám răng thẩm mỹ bằng Composite'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Trám răng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Bọc răng sứ'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Bọc răng sứ')),

-- Vệ sinh răng miệng, súc miệng bằng nước muối: Cạo vôi răng, Điều trị bệnh lý nướu
(false, (SELECT service_id FROM service WHERE service_name = 'Cạo vôi răng'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Vệ sinh răng miệng')),
(false, (SELECT service_id FROM service WHERE service_name = 'Điều trị bệnh lý nướu'), (SELECT treatment_id FROM treatment WHERE treatment_name = 'Súc miệng bằng nước muối'));



