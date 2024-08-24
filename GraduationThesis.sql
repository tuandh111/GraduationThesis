     use GraduationThesis ;

     INSERT INTO role (description,is_deleted,role_name) VALUES
          ('Admin',0,'ADMIN'),
          ('Nhân viên',0,'NHAN_VIEN'),
          ('Bác sĩ',0,'BAC_SI'),
          ('Bệnh nhân',0,'BENH_NHAN'),
          ('Lễ tân',0,'LE_TAN');


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

INSERT INTO service (service_name, price, time_estimate, service_type_id, is_deleted, description, imageurl) VALUES
     ('Khám tổng quát', 0, 25, 1, false, 'Khám tổng quát giúp kiểm tra tình trạng sức khỏe tổng thể và phát hiện sớm các vấn đề.', 'https://www.vinmec.com/static/uploads/small_20210327_095104_900316_chup_xquang_rang_max_1800x1800_jpg_d64ce1176d.jpg'),
     ('X quang kỹ thuật số (1 phim)', 20000, 15, 2, false, 'Chụp X quang kỹ thuật số để kiểm tra các vấn đề bên trong cơ thể.', 'https://medlatec.vn/media/2403/content/20230222_kham-tong-quat-bao-nhieu-tien-1.jpg'),
     ('X quang toàn cảnh', 100000, 15, 2, false, 'Chụp X quang toàn cảnh để kiểm tra toàn bộ vùng hàm mặt.', 'https://lh5.googleusercontent.com/proxy/zr1zSdk8jWsDrkFXxt4z8Oz3jgGOgF2EpquOvu9VjDgrtmntWFvZdS0qtj2N-S0iB8xmEVfOICjs3cRMuMJjrI-DuXsFpS4fWk1gZjuex2miEHtAUtGy4EE'),
     ('X quang sọ nghiêng', 100000, 15, 2, false, 'Chụp X quang sọ nghiêng để kiểm tra cấu trúc sọ từ góc nghiêng.', 'https://nhakhoasmileone.vn/uploaded/2022/1-chup-xquang-truoc-khi-nieng-rang%20(2).png'),
     ('X quang sọ thẳng', 100000, 15, 2, false, 'Chụp X quang sọ thẳng để kiểm tra cấu trúc sọ từ góc thẳng.', 'https://login.medlatec.vn//ImagePath/images/20200319/20200319_chup-x-quang-rang-khon-01.jpg'),
     ('X quang CT răng hàm mặt - 2 hàm', 600000, 15, 2, false, 'Chụp CT để kiểm tra chi tiết các răng và cấu trúc hàm mặt.', 'https://login.medlatec.vn//ImagePath/images/20200323/20200323_chup-ct-rang-01.jpg'),
     ('X quang CT răng hàm mặt - 1 hàm', 500000, 15, 2, false, 'Chụp CT để kiểm tra chi tiết một hàm răng và cấu trúc hàm mặt.', 'https://ptddatviet.vn/Content/Fileuploads/images/chup-phim-hien-dai-cone-beam-ct%20-2.jpg'),
     ('Cạo vôi răng', 250000, 20, 3, false, 'Cạo vôi răng giúp loại bỏ mảng bám và vôi răng.', 'https://cdn.diemnhangroup.com/nhakhoashark.vn/2022/11/Cao-voi-rang-1.jpg'),
     ('Trám bít hố rãnh', 200000, 20, 3, false, 'Trám bít hố rãnh giúp bảo vệ răng khỏi sâu và tổn thương.', 'https://cdn.diemnhangroup.com/nhakhoashark.vn/2023/11/Tram-bit-ho-ranh-2.jpg'),
     ('Trám răng bằng Composite', 150000, 35, 4, false, 'Trám răng bằng vật liệu Composite để phục hồi chức năng răng.', 'https://nhakhoamientay.com/wp-content/uploads/2021/04/tram-rang-composite-la-gi.jpg'),
     ('Trám răng thẩm mỹ bằng Composite', 400000, 35, 4, false, 'Trám răng thẩm mỹ bằng Composite giúp cải thiện vẻ ngoài của răng.', 'https://nhakhoakim.com/wp-content/uploads/2018/08/Tram-rang-composite.jpg'),
     ('Chữa tủy', 800000, 35, 4, false, 'Điều trị tủy răng để giải quyết các vấn đề liên quan đến tủy.', 'https://soradental.com/wp-content/uploads/2020/05/top-6-nha-khoa-chua-tuy-rang-gioi-nhat-tai-tphcm.jpg'),
     ('Nạo túi nha chu', 200000, 35, 4, false, 'Nạo túi nha chu giúp loại bỏ vi khuẩn và mảng bám trong nướu.', 'https://nhakhoaantamsaigon.vn/thumbs/600x423x2/upload/news/phau-thuat-nao-tui-loi-1-1-600x423-2473.jpg'),
     ('Nhổ răng sữa trẻ em', 50000, 15, 5, false, 'Nhổ răng sữa cho trẻ em khi răng đã rụng hoặc cần loại bỏ.', 'https://nhakhoakim.com/wp-content/uploads/2018/08/nha-khoa-tre-em-o-tpchm-1.jpg'),
     ('Nhổ răng vĩnh viễn mọc thẳng', 400000, 20, 5, false, 'Nhổ răng vĩnh viễn mọc thẳng để giải quyết các vấn đề răng miệng.', 'https://nhakhoanhantam.com/stmresource/files/nho-rang/rang-nho-roi-co-moc-lai-khong-cach-phuc-hoi-rang.jpg'),
     ('Nhổ răng khôn hàm dưới', 1500000, 45, 5, false, 'Nhổ răng khôn hàm dưới khi cần thiết để giảm đau và ngăn ngừa các vấn đề.', 'https://nhakhoanhantam.com/stmresource/files/nho-rang/nhung-luu-y-khi-nho-rang-khon-ham-duoi.jpg'),
     ('Nhổ răng khôn hàm trên', 800000, 45, 5, false, 'Nhổ răng khôn hàm trên khi cần thiết để giảm đau và ngăn ngừa các vấn đề.', 'https://suckhoedoisong.qltns.mediacdn.vn/324455921873985536/2024/1/6/nho-rang1-170451102817991690027.jpg'),
     ('Tẩy trắng răng tại phòng khám', 1500000, 40, 6, false, 'Dịch vụ tẩy trắng răng tại phòng khám để làm sáng màu răng.', 'https://kandental.com/wp-content/uploads/2018/04/TAY-TRANG-BANG-DEN.jpg'),
     ('Tẩy trắng răng tại nhà', 1000000, 40, 6, false, 'Tẩy trắng răng tại nhà với các sản phẩm chuyên dụng.', 'https://nhakhoalinhxuan.com/wp-content/uploads/2018/12/mang-tay-trang-rang-tai-nha.jpg'),
     ('Tẩy trắng răng kết hợp', 2200000, 40, 6, false, 'Kết hợp tẩy trắng răng tại phòng khám và tại nhà để đạt hiệu quả tối ưu.', 'https://nhakhoacheese.vn/wp-content/uploads/2022/08/tay-trang-rang_taimuihongsg.jpeg'),
     ('Tẩy trắng nội nha', 500000, 40, 6, false, 'Tẩy trắng răng nội nha để làm sáng màu răng từ bên trong.', 'https://nhakhoaaquacare.com/wp-content/uploads/2016/05/tay-trang-rang-2.jpg'),
     ('Chốt, cùi giả', 1000000, 40, 7, false, 'Chốt và cùi giả để thay thế răng bị hư hỏng.', 'https://cdn.nhathuoclongchau.com.vn/unsafe/https://cms-prod.s3-sgn09.fptcloud.com/tong_quan_ve_tai_tao_cui_rang_va_cac_diem_can_luu_y_1_Cropped_19005ae560.jpg'),
     ('Răng sứ tháo lắp', 800000, 30, 8, false, 'Răng sứ tháo lắp giúp thay thế các răng bị mất hoặc bị hư hỏng.', 'https://nhakhoadaiviet.vn/wp-content/uploads/2015/12/rang-su-thao-l%C4%83p.png');

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

INSERT INTO appointment_status (description,is_deleted,status) VALUES
	 ('Cuộc hẹn đã được đặt thành công và đang chờ đến ngày giờ thực hiện.',0,'Đã Đặt'),
	 ('Cuộc hẹn đã được xác nhận bởi nhân viên phòng khám.',0,'Đã Xác Nhận'),
	 ('Cuộc hẹn đang được thực hiện',0,'Đang Diễn Ra'),
	 ('Cuộc hẹn đã được hoàn thành',0,'Hoàn Thành'),
	 ('Cuộc hẹn đã bị hủy bởi khách hàng hoặc phòng khám.',0,'Đã Hủy'),
	 ('Khách hàng không đến theo lịch hẹn.',0,'Không Đến'),
	 ('Cuộc hẹn đã được dời lại sang ngày giờ khác',0,'Hoãn');

INSERT INTO GraduationThesis.department (department_name,description,is_deleted) VALUES
	 ('Phòng Lễ Tân và Tiếp Đón Bệnh Nhân',NULL,0),
	 ('Phòng Khám Tổng Quát',NULL,0),
	 ('Phòng Chụp X-quang',NULL,0),
	 ('Phòng Điều Trị',NULL,0),
	 ('Phòng Phục Hình',NULL,0),
	 ('Phòng Vệ Sinh Răng Miệng',NULL,0),
	 ('Phòng Quản Lý và Hành Chính',NULL,0),
	 ('Phòng Kỹ Thuật và Vật Tư',NULL,0);
INSERT INTO shift (begin_time,description,end_time,is_deleted,name) VALUES
	 ('07:30:00','Thời gian làm việc từ 7g30 đến 11g 30','11:30:00',0,'Sáng'),
	 ('12:30:00','Thời gian làm việc từ 12g30 đến 16g 30','16:30:00',0,'Chiều'),
	 ('17:30:00','Thời gian làm việc từ 17g30 đến 21g 30','21:30:00',0,'Tối');
INSERT INTO time_of_shift (begin_time,end_time,is_deleted,shift_id) VALUES
	 ('07:30:00','08:00:00',0,1),
	 ('08:00:00','08:30:00',0,1),
	 ('08:30:00','09:00:00',0,1),
	 ('09:00:00','09:30:00',0,1),
	 ('09:30:00','10:00:00',0,1),
	 ('10:00:00','10:30:00',0,1),
	 ('10:30:00','11:00:00',0,1),
	 ('11:00:00','11:30:00',0,1),
	 ('12:30:00','13:00:00',0,2),
	 ('13:00:00','13:30:00',0,2),
	 ('13:30:00','14:00:00',0,2),
	 ('14:00:00','14:30:00',0,2),
	 ('14:30:00','15:00:00',0,2),
	 ('15:00:00','15:30:00',0,2),
	 ('15:30:00','16:00:00',0,2),
	 ('16:00:00','16:30:00',0,2),
	 ('17:30:00','18:00:00',0,3),
	 ('18:00:00','18:30:00',0,3),
	 ('18:30:00','19:00:00',0,3),
	 ('19:00:00','19:30:00',0,3),
	 ('19:30:00','20:00:00',0,3),
	 ('20:00:00','21:30:00',0,3);
	 
     INSERT INTO doctor (address, birthday, degrees, full_name, gender, image, is_deleted, phone_number, signature, specialty_id) VALUES
          ('Ninh Kiều, Cần Thơ', '1975-04-18', '1', 'Đinh Lê Hoàn', 'MALE', NULL, 0, '0912345678', 'abc', 4),
          ('Bình Thủy, Cần Thơ', '1982-09-25', '1', 'Bùi Khánh Nhung', 'MALE', NULL, 0, '0912345679', 'abc', 1),
          ('Cái Răng, Cần Thơ', '1988-07-14', '1', 'Nguyễn Hà Lan', 'FEMALE', NULL, 0, '0912345680', NULL, 1),
          ('Ô Môn, Cần Thơ', '1979-11-03', '1', 'Trần Trung Ngạn', 'MALE', NULL, 0, '0912345681', NULL, 1),
          ('Thốt Nốt, Cần Thơ', '1980-05-27', '1', 'Vũ Anh Hùng', 'MALE', NULL, 0, '0912345682', '1', 2),
          ('Phong Điền, Cần Thơ', '1976-08-21', '1', 'Hoàng Công Trang', 'MALE', NULL, 0, '0912345683', '3', 2),
          ('Ninh Kiều, Cần Thơ', '1984-02-11', '1', 'Lê Công Lan', 'MALE', NULL, 0, '0912345684', '4', 3),
          ('Cái Răng, Cần Thơ', '1978-12-30', '1', 'Trần Văn Nhung', 'MALE', NULL, 0, '0912345685', '2', 4),
          ('Bình Thủy, Cần Thơ', '1986-03-15', '1', 'Bùi Minh Trang', 'MALE', NULL, 0, '0912345686', '2', 5),
          ('Thốt Nốt, Cần Thơ', '1983-06-09', '1', 'Lê Gia Lan', 'MALE', NULL, 0, '0912345687', '2', 6),
          ('Phong Điền, Cần Thơ', '1981-01-28', '1', 'Trần Công Hùng', 'MALE', NULL, 0, '0912345688', '2', 7),
          ('Ô Môn, Cần Thơ', '1974-10-06', '1', 'Đặng Văn Hùng', 'MALE', NULL, 0, '0912345689', '2', 8),
          ('Ninh Kiều, Cần Thơ', '1977-09-12', '1', 'Nguyễn Gia Tuấn', 'MALE', NULL, 0, '0912345690', '2', 9),
          ('Cái Răng, Cần Thơ', '1989-05-23', '1', 'Nguyễn Hà Lan', 'FEMALE', NULL, 0, '0912345691', '2', 10),
          ('Ninh Kiều, Cần Thơ', '1987-07-01', '1', 'Phạm Thanh Bình', 'MALE', NULL, 0, '0912345692', 'abc', 4),
          ('Bình Thủy, Cần Thơ', '1973-02-19', '1', 'Lê Thị Mai', 'FEMALE', NULL, 0, '0912345693', 'abc', 1),
          ('Cái Răng, Cần Thơ', '1980-11-07', '1', 'Trần Văn Hùng', 'MALE', NULL, 0, '0912345694', NULL, 1),
          ('Ô Môn, Cần Thơ', '1986-04-14', '1', 'Nguyễn Thị Thanh', 'FEMALE', NULL, 0, '0912345695', NULL, 1),
          ('Thốt Nốt, Cần Thơ', '1971-08-05', '1', 'Bùi Thị Hồng', 'FEMALE', NULL, 0, '0912345696', '1', 2),
          ('Phong Điền, Cần Thơ', '1983-12-20', '1', 'Vũ Văn Tuấn', 'MALE', NULL, 0, '0912345697', '3', 2),
          ('Ninh Kiều, Cần Thơ', '1989-09-09', '1', 'Nguyễn Thị Lan', 'FEMALE', NULL, 0, '0912345698', '4', 3),
          ('Cái Răng, Cần Thơ', '1979-01-25', '1', 'Phạm Quốc Anh', 'MALE', NULL, 0, '0912345699', '2', 4),
          ('Bình Thủy, Cần Thơ', '1981-03-22', '1', 'Đặng Thị Hương', 'FEMALE', NULL, 0, '0912345700', '2', 5),
          ('Thốt Nốt, Cần Thơ', '1975-07-18', '1', 'Lê Văn Cường', 'MALE', NULL, 0, '0912345701', '2', 6),
          ('Phong Điền, Cần Thơ', '1984-11-03', '1', 'Nguyễn Hữu Nam', 'MALE', NULL, 0, '0912345702', '2', 7),
          ('Ô Môn, Cần Thơ', '1978-06-28', '1', 'Bùi Văn Khôi', 'MALE', NULL, 0, '0912345703', '2', 8),
          ('Ninh Kiều, Cần Thơ', '1980-10-15', '1', 'Phạm Văn Hậu', 'MALE', NULL, 0, '0912345704', '2', 9),
          ('Cái Răng, Cần Thơ', '1982-12-09', '1', 'Võ Thị Lệ', 'FEMALE', NULL, 0, '0912345705', '2', 10),
          ('Ninh Kiều, Cần Thơ', '1987-02-03', '1', 'Nguyễn Thị Phương', 'FEMALE', NULL, 0, '0912345706', 'abc', 4),
          ('Bình Thủy, Cần Thơ', '1974-05-14', '1', 'Phạm Văn Dương', 'MALE', NULL, 0, '0912345707', 'abc', 1),
          ('Cái Răng, Cần Thơ', '1985-07-30', '1', 'Trần Thị Mai', 'FEMALE', NULL, 0, '0912345708', NULL, 1),
          ('Ô Môn, Cần Thơ', '1979-09-01', '1', 'Lê Văn Hùng', 'MALE', NULL, 0, '0912345709', NULL, 1),
          ('Thốt Nốt, Cần Thơ', '1983-03-05', '1', 'Nguyễn Thị Tuyết', 'FEMALE', NULL, 0, '0912345710', '1', 2);

     INSERT INTO `dental_staff` (`dental_staff_id`, `address`, `birthday`, `fullname`, `gender`, `imageurl`, `is_deleted`, `phone_number`, `department_id`) VALUES
     (NULL, 'Ninh Kiều, Cần Thơ', '15-02-1985', 'Nguyễn Thị Lan', 'FEMALE', NULL, b'00000', '0901234567', '1'),
     (NULL, 'Cái Răng, Cần Thơ', '22-03-1990', 'Trần Văn An', 'MALE', NULL, b'00000', '0912345678', '2'),
     (NULL, 'Bình Thủy, Cần Thơ', '30-04-1987', 'Lê Thị Mai', 'FEMALE', NULL, b'00000', '0923456789', '3'),
     (NULL, 'Ô Môn, Cần Thơ', '18-05-1988', 'Hoàng Văn Đạt', 'MALE', NULL, b'00000', '0934567890', '4'),
     (NULL, 'Thốt Nốt, Cần Thơ', '10-06-1992', 'Phạm Thị Hạnh', 'FEMALE', NULL, b'00000', '0945678901', '5'),
     (NULL, 'Phong Điền, Cần Thơ', '12-07-1984', 'Nguyễn Văn Bình', 'MALE', NULL, b'00000', '0956789012', '6'),
     (NULL, 'Cờ Đỏ, Cần Thơ', '25-08-1989', 'Võ Thị Tuyết', 'FEMALE', NULL, b'00000', '0967890123', '7'),
     (NULL, 'Vĩnh Thạnh, Cần Thơ', '05-09-1991', 'Trịnh Văn Sơn', 'MALE', NULL, b'00000', '0978901234', '8'),
     (NULL, 'Ninh Kiều, Cần Thơ', '17-10-1986', 'Lâm Thị Hoa', 'FEMALE', NULL, b'00000', '0989012345', '9'),
     (NULL, 'Cái Răng, Cần Thơ', '28-11-1983', 'Nguyễn Văn Phúc', 'MALE', NULL, b'00000', '0990123456', '10'),
     (NULL, 'Bình Thủy, Cần Thơ', '14-12-1990', 'Lê Văn Hoàng', 'MALE', NULL, b'00000', '0902345678', '11'),
     (NULL, 'Ô Môn, Cần Thơ', '20-01-1985', 'Đặng Thị Thanh', 'FEMALE', NULL, b'00000', '0913456789', '1'),
     (NULL, 'Thốt Nốt, Cần Thơ', '12-02-1992', 'Trần Thị Mai', 'FEMALE', NULL, b'00000', '0924567890', '2'),
     (NULL, 'Phong Điền, Cần Thơ', '03-03-1987', 'Nguyễn Văn Hoàng', 'MALE', NULL, b'00000', '0935678901', '3'),
     (NULL, 'Vĩnh Thạnh, Cần Thơ', '15-04-1991', 'Lê Thị Bích', 'FEMALE', NULL, b'00000', '0946789012', '4'),
     (NULL, 'Cờ Đỏ, Cần Thơ', '07-05-1986', 'Trịnh Văn Tuấn', 'MALE', NULL, b'00000', '0957890123', '5'),
     (NULL, 'Ninh Kiều, Cần Thơ', '22-06-1989', 'Nguyễn Thị Lan', 'FEMALE', NULL, b'00000', '0968901234', '6'),
     (NULL, 'Cái Răng, Cần Thơ', '30-07-1984', 'Phạm Văn Dũng', 'MALE', NULL, b'00000', '0979012345', '7'),
     (NULL, 'Bình Thủy, Cần Thơ', '12-08-1993', 'Võ Thị Mai', 'FEMALE', NULL, b'00000', '0980123456', '8'),
     (NULL, 'Ô Môn, Cần Thơ', '23-09-1987', 'Lâm Văn Trung', 'MALE', NULL, b'00000', '0991234567', '9'),
     (NULL, 'Thốt Nốt, Cần Thơ', '11-10-1989', 'Nguyễn Văn Thái', 'MALE', NULL, b'00000', '0902345678', '10'),
     (NULL, 'Phong Điền, Cần Thơ', '05-11-1990', 'Lê Thị Nhung', 'FEMALE', NULL, b'00000', '0913456789', '11'),
     (NULL, 'Vĩnh Thạnh, Cần Thơ', '15-12-1991', 'Trần Thị Hoa', 'FEMALE', NULL, b'00000', '0924567890', '1'),
     (NULL, 'Cờ Đỏ, Cần Thơ', '27-01-1986', 'Nguyễn Văn Phú', 'MALE', NULL, b'00000', '0935678901', '2'),
     (NULL, 'Ninh Kiều, Cần Thơ', '09-02-1992', 'Lê Văn Thanh', 'MALE', NULL, b'00000', '0946789012', '3'),
     (NULL, 'Cái Răng, Cần Thơ', '18-03-1990', 'Phạm Thị Lan', 'FEMALE', NULL, b'00000', '0957890123', '4'),
     (NULL, 'Bình Thủy, Cần Thơ', '26-04-1987', 'Võ Văn Hòa', 'MALE', NULL, b'00000', '0968901234', '5'),
     (NULL, 'Ô Môn, Cần Thơ', '05-05-1988', 'Nguyễn Thị Hồng', 'FEMALE', NULL, b'00000', '0979012345', '6'),
     (NULL, 'Thốt Nốt, Cần Thơ', '12-06-1991', 'Lâm Thị Lan', 'FEMALE', NULL, b'00000', '0980123456', '7'),
     (NULL, 'Phong Điền, Cần Thơ', '22-07-1989', 'Trịnh Văn Lâm', 'MALE', NULL, b'00000', '0991234567', '8'),
     (NULL, 'Vĩnh Thạnh, Cần Thơ', '15-08-1984', 'Nguyễn Thị Hòa', 'FEMALE', NULL, b'00000', '0902345678', '9'),
     (NULL, 'Cờ Đỏ, Cần Thơ', '04-09-1993', 'Lê Văn Hải', 'MALE', NULL, b'00000', '0913456789', '10'),
     (NULL, 'Ninh Kiều, Cần Thơ', '21-10-1990', 'Phạm Thị Ngọc', 'FEMALE', NULL, b'00000', '0924567890', '11'),
     (NULL, 'Cái Răng, Cần Thơ', '15-11-1985', 'Nguyễn Văn Sơn', 'MALE', NULL, b'00000', '0935678901', '1'),
     (NULL, 'Bình Thủy, Cần Thơ', '02-12-1989', 'Lê Thị Thanh', 'FEMALE', NULL, b'00000', '0946789012', '2'),
     (NULL, 'Ô Môn, Cần Thơ', '20-01-1991', 'Trịnh Văn Phúc', 'MALE', NULL, b'00000', '0957890123', '3'),
     (NULL, 'Thốt Nốt, Cần Thơ', '28-02-1987', 'Nguyễn Thị Minh', 'FEMALE', NULL, b'00000', '0968901234', '4'),
     (NULL, 'Phong Điền, Cần Thơ', '15-03-1990', 'Lâm Văn Bình', 'MALE', NULL, b'00000', '0979012345', '5'),
     (NULL, 'Vĩnh Thạnh, Cần Thơ', '04-04-1988', 'Phạm Thị Mai', 'FEMALE', NULL, b'00000', '0980123456', '6'),
     (NULL, 'Cờ Đỏ, Cần Thơ', '23-05-1993', 'Nguyễn Văn Hưng', 'MALE', NULL, b'00000', '0991234567', '7'),
     (NULL, 'Ninh Kiều, Cần Thơ', '12-06-1986', 'Lê Thị Bích', 'FEMALE', NULL, b'00000', '0902345678', '8'),
     (NULL, 'Cái Răng, Cần Thơ', '05-07-1991', 'Trịnh Văn Phú', 'MALE', NULL, b'00000', '0913456789', '9'),
     (NULL, 'Bình Thủy, Cần Thơ', '18-08-1989', 'Nguyễn Thị Thanh', 'FEMALE', NULL, b'00000', '0924567890', '10'),
     (NULL, 'Ô Môn, Cần Thơ', '30-09-1990', 'Lâm Văn Tuấn', 'MALE', NULL, b'00000', '0935678901', '11');

INSERT INTO doctor_schedule (create_at, date, is_deleted, update_at, doctor_id, shift_id, is_available) VALUES

(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 1, 1, b'1'),
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 1, 2, b'1'),
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 1, 3, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 1, 1, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 1, 2, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 1, 3, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 1, 1, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 1, 2, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 1, 3, b'1'),

-- Doctor 2
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 2, 1, b'1'),
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 2, 2, b'1'),
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 2, 3, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 2, 1, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 2, 2, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 2, 3, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 2, 1, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 2, 2, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 2, 3, b'1'),

-- Doctor 3
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 3, 1, b'1'),
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 3, 2, b'1'),
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 3, 3, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 3, 1, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 3, 2, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 3, 3, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 3, 1, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 3, 2, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 3, 3, b'1'),

-- Doctor 4
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 4, 1, b'1'),
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 4, 2, b'1'),
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 4, 3, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 4, 1, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 4, 2, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 4, 3, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 4, 1, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 4, 2, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 4, 3, b'1'),

-- Doctor 5
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 5, 1, b'1'),
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 5, 2, b'1'),
(NOW(), '2024-07-01 00:00:00.000000', b'0', NULL, 5, 3, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 5, 1, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 5, 2, b'1'),
(NOW(), '2024-07-02 00:00:00.000000', b'0', NULL, 5, 3, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 5, 1, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 5, 2, b'1'),
(NOW(), '2024-07-03 00:00:00.000000', b'0', NULL, 5, 3, b'1');

INSERT INTO `post` (`post_id`, `body`, `create_date`, `image`, `is_deleted`, `title`, `dental_staff_id`) VALUES 
          (NULL, 'Phòng khám Tooth Teeth đã triển khai chương trình ưu đãi đặc biệt từ 01/07/2024, giảm giá tới 30% cho các dịch vụ nha khoa thẩm mỹ.', '2024-06-15', NULL, b'00000', 'ƯU ĐÃI ĐẶC BIỆT TỪ TOOTH TEETH', 5),
          (NULL, 'Tháng 7 này, phòng khám của chúng tôi đã thành công trong việc điều trị tẩy trắng răng cho hơn 50 khách hàng.', '2024-07-03', NULL, b'00000', 'HƠN 50 KHÁCH HÀNG ĐÃ TẨY TRẮNG RĂNG THÀNH CÔNG', 12),
          (NULL, 'Nha sĩ nổi tiếng Đinh Lê Hoàn đã chia sẻ những bí quyết giữ gìn răng miệng tốt nhất trong mùa hè.', '2024-06-20', NULL, b'00000', 'BÍ QUYẾT GIỮ GÌN RĂNG MIỆNG MÙA HÈ', 7),
          (NULL, 'Phòng khám Tooth Teeth hiện đang cung cấp dịch vụ chỉnh nha với giá ưu đãi chưa từng có.', '2024-07-15', NULL, b'00000', 'CHỈNH NHA GIÁ ƯU ĐÃI', 22),
          (NULL, 'Chương trình khám răng miễn phí cho trẻ em từ 5-12 tuổi sẽ diễn ra vào cuối tháng 7 tại phòng khám.', '2024-06-25', NULL, b'00000', 'KHÁM RĂNG MIỄN PHÍ CHO TRẺ EM', 9),
          (NULL, 'Cập nhật mới nhất về dịch vụ bọc răng sứ tại Tooth Teeth - giải pháp hoàn hảo cho nụ cười trắng sáng.', '2024-07-10', NULL, b'00000', 'BỌC RĂNG SỨ HOÀN HẢO', 34),
          (NULL, 'Phòng khám vừa chào đón thêm một nha sĩ mới với kinh nghiệm dày dặn, Nha sĩ Trần Thị Linh.', '2024-06-30', NULL, b'00000', 'CHÀO ĐÓN NHA SĨ MỚI', 18),
          (NULL, 'Phòng khám của chúng tôi đã thực hiện hơn 200 ca nhổ răng khôn an toàn trong tháng 6.', '2024-07-05', NULL, b'00000', 'HƠN 200 CA NHỔ RĂNG KHÔN AN TOÀN', 29),
          (NULL, 'Tìm hiểu về lợi ích của việc kiểm tra răng miệng định kỳ tại Tooth Teeth.', '2024-06-18', NULL, b'00000', 'LỢI ÍCH CỦA KIỂM TRA RĂNG MIỆNG ĐỊNH KỲ', 11),
          (NULL, 'Tooth Teeth đã tiến hành cải tiến công nghệ làm răng giả giúp nâng cao chất lượng dịch vụ.', '2024-07-20', NULL, b'00000', 'CẢI TIẾN CÔNG NGHỆ LÀM RĂNG GIẢ', 39);

     INSERT INTO patient (citizen_identification_number,`type`,birthday,full_name,gender,imageurl,is_deleted,phone_number, address) VALUES
          ('123124125',NULL,'2003-10-13 20:13:33','Bệnh Tật ','MALE',NULL,0,'01234567894','Cần Thơ'),
          ('333444555',NULL,'1999-01-01 00:00:00','Đặng Hoàng Tuấn','MALE','abc.jpg',0,'0909123456','Cần Thơ'),
          ('654789333',NULL,'1999-01-01 00:00:00','Vĩ Khang','MALE','abc.jpg',0,'0909888777','Cần Thơ'),
          ('000111222',NULL,'1999-01-01 00:00:00','Nguyễn Văn A','MALE','abc.jpg',0,'0909000001','Cần Thơ'),
          ('111222333',NULL,'1999-01-01 00:00:00','Trần Thị B','FEMALE','abc.jpg',0,'0909000002','Cần Thơ'),
          ('222333444',NULL,'1999-01-01 00:00:00','Lê Văn C','MALE','abc.jpg',0,'0909000003','Cần Thơ'),
          ('333444555',NULL,'1999-01-01 00:00:00','Phạm Thị D','FEMALE','abc.jpg',0,'0909000004','Cần Thơ'),
          ('444555666',NULL,'1999-01-01 00:00:00','Đỗ Văn E','MALE','abc.jpg',0,'0909000005','Cần Thơ'),
          ('555666777',NULL,'1999-01-01 00:00:00','Vũ Thị F','FEMALE','abc.jpg',0,'0909000006','Cần Thơ'),
          ('666777888',NULL,'1999-01-01 00:00:00','Bùi Văn G','MALE','abc.jpg',0,'0909000007','Cần Thơ'),
          ('777888999',NULL,'1999-01-01 00:00:00','Nguyễn Thị H','FEMALE','abc.jpg',0,'0909000008','Cần Thơ'),
          ('888999000',NULL,'1999-01-01 00:00:00','Trần Văn I','MALE','abc.jpg',0,'0909000009','Cần Thơ'),
          ('999000111',NULL,'1999-01-01 00:00:00','Lê Thị J','FEMALE','abc.jpg',0,'0909000010','Cần Thơ'),
          ('000111333',NULL,'1999-01-01 00:00:00','Phạm Văn K','MALE','abc.jpg',0,'0909000011','Cần Thơ'),
          ('111222444',NULL,'1999-01-01 00:00:00','Đỗ Thị L','FEMALE','abc.jpg',0,'0909000012','Cần Thơ'),
          ('222333555',NULL,'1999-01-01 00:00:00','Vũ Văn M','MALE','abc.jpg',0,'0909000013','Cần Thơ'),
          ('333444666',NULL,'1999-01-01 00:00:00','Bùi Thị N','FEMALE','abc.jpg',0,'0909000014','Cần Thơ'),
          ('444555777',NULL,'1999-01-01 00:00:00','Nguyễn Văn O','MALE','abc.jpg',0,'0909000015','Cần Thơ'),
          ('555666888',NULL,'1999-01-01 00:00:00','Trần Thị P','FEMALE','abc.jpg',0,'0909000016','Cần Thơ'),
          ('666777999',NULL,'1999-01-01 00:00:00','Lê Văn Q','MALE','abc.jpg',0,'0909000017','Cần Thơ'),
          ('777888000',NULL,'1999-01-01 00:00:00','Phạm Thị R','FEMALE','abc.jpg',0,'0909000018','Cần Thơ'),
          ('888999111',NULL,'1999-01-01 00:00:00','Đỗ Văn S','MALE','abc.jpg',0,'0909000019','Cần Thơ'),
          ('999000222',NULL,'1999-01-01 00:00:00','Vũ Thị T','FEMALE','abc.jpg',0,'0909000020','Cần Thơ'),
          ('000111444',NULL,'1999-01-01 00:00:00','Bùi Văn U','MALE','abc.jpg',0,'0909000021','Cần Thơ'),
          ('111222555',NULL,'1999-01-01 00:00:00','Nguyễn Thị V','FEMALE','abc.jpg',0,'0909000022','Cần Thơ'),
          ('222333666',NULL,'1999-01-01 00:00:00','Trần Văn W','MALE','abc.jpg',0,'0909000023','Cần Thơ'),
          ('333444777',NULL,'1999-01-01 00:00:00','Lê Thị X','FEMALE','abc.jpg',0,'0909000024','Cần Thơ'),
          ('444555888',NULL,'1999-01-01 00:00:00','Phạm Văn Y','MALE','abc.jpg',0,'0909000025','Cần Thơ'),
          ('555666999',NULL,'1999-01-01 00:00:00','Đỗ Thị Z','FEMALE','abc.jpg',0,'0909000026','Cần Thơ'),
          ('666777000',NULL,'1999-01-01 00:00:00','Vũ Văn AA','MALE','abc.jpg',0,'0909000027','Cần Thơ'),
          ('777888111',NULL,'1999-01-01 00:00:00','Bùi Thị BB','FEMALE','abc.jpg',0,'0909000028','Cần Thơ'),
          ('888999222',NULL,'1999-01-01 00:00:00','Nguyễn Văn CC','MALE','abc.jpg',0,'0909000029','Cần Thơ'),
          ('999000333',NULL,'1999-01-01 00:00:00','Trần Thị DD','FEMALE','abc.jpg',0,'0909000030','Cần Thơ'),
          ('000111555',NULL,'1999-01-01 00:00:00','Lê Văn EE','MALE','abc.jpg',0,'0909000031','Cần Thơ'),
          ('111222666',NULL,'1999-01-01 00:00:00','Phạm Thị FF','FEMALE','abc.jpg',0,'0909000032','Cần Thơ'),
          ('222333777',NULL,'1999-01-01 00:00:00','Đỗ Văn GG','MALE','abc.jpg',0,'0909000033','Cần Thơ'),
          ('333444888',NULL,'1999-01-01 00:00:00','Vũ Thị HH','FEMALE','abc.jpg',0,'0909000034','Cần Thơ'),
          ('444555999',NULL,'1999-01-01 00:00:00','Bùi Văn II','MALE','abc.jpg',0,'0909000035','Cần Thơ'),
          ('555666000',NULL,'1999-01-01 00:00:00','Nguyễn Thị JJ','FEMALE','abc.jpg',0,'0909000036','Cần Thơ'),
          ('666777111',NULL,'1999-01-01 00:00:00','Trần Văn KK','MALE','abc.jpg',0,'0909000037','Cần Thơ'),
          ('777888222',NULL,'1999-01-01 00:00:00','Lê Thị LL','FEMALE','abc.jpg',0,'0909000038','Cần Thơ'),
          ('888999333',NULL,'1999-01-01 00:00:00','Phạm Văn MM','MALE','abc.jpg',0,'0909000039','Cần Thơ'),
          ('999000444',NULL,'1999-01-01 00:00:00','Đỗ Thị NN','FEMALE','abc.jpg',0,'0909000040','Cần Thơ'),
          ('000111666',NULL,'1999-01-01 00:00:00','Vũ Văn OO','MALE','abc.jpg',0,'0909000041','Cần Thơ'),
          ('111222777',NULL,'1999-01-01 00:00:00','Bùi Thị PP','FEMALE','abc.jpg',0,'0909000042','Cần Thơ');

     insert into account (email,is_deleted,password, status,patient_id,role_id) values
     ('hoangtuan97531@gmail.com',false,'$2a$10$Q6cp0YJP3zIUtym1BYi.xuJmOja6/0BANwDemhQ4ldjBLCLYPQ.Em',true,1,4),
     ('dkhang709@gmail.com@gmail.com',false,'$2a$10$Q6cp0YJP3zIUtym1BYi.xuJmOja6/0BANwDemhQ4ldjBLCLYPQ.Em',true,2,4);
          -- Acccont doctor
     insert into account (email,is_deleted,password, status,doctor_id,role_id) values
     ('trung2894@gmail.com',false,'$2a$10$Q6cp0YJP3zIUtym1BYi.xuJmOja6/0BANwDemhQ4ldjBLCLYPQ.Em',true,1,3);
     -- Acount lễ tân
     insert into account (email,is_deleted,password, status,dental_staff_id,role_id) values
     ('khangdvpc05402@fpt.edu.vn',false,'$2a$10$Q6cp0YJP3zIUtym1BYi.xuJmOja6/0BANwDemhQ4ldjBLCLYPQ.Em',true,1,5);
     -- Acount admin,nhân viên
     insert into account (email,is_deleted,password, status,dental_staff_id,role_id) values
     ('hoanledinhlv100@gmail.com',false,'$2a$10$Q6cp0YJP3zIUtym1BYi.xuJmOja6/0BANwDemhQ4ldjBLCLYPQ.Em',true,3,1),
     ('ducanhnhatbui@gmail.com',false,'$2a$10$Q6cp0YJP3zIUtym1BYi.xuJmOja6/0BANwDemhQ4ldjBLCLYPQ.Em',true,2,2);

INSERT INTO appointment_type ( description, is_deleted, type_name) VALUES ('Khám', b'0', 'Tiểu phẫu');
INSERT INTO appointment_type ( description, is_deleted, type_name) VALUES ( 'Khám', b'0', 'Khám');

INSERT INTO patient (patient_id, citizen_identification_number,type, birthday, full_name, gender, imageurl, is_deleted, phone_number) VALUES (NULL, '123124125', NULL, '2003-10-13 20:13:33.000000', 'Bệnh Tật ', 'MALE', NULL, b'0', '01234567894');

INSERT INTO appointment (appointment_id, appointment_date, create_at, is_deleted, note, appointment_patient_record_id, appointment_status_id, appointment_type_id, dental_staff_id, doctor_id, patient_id) VALUES (NULL, '2024-07-01', '2024-06-28', b'0', 'hay', NULL, 1, 1, NULL, 1, 1);
INSERT INTO appointment (appointment_id, appointment_date, create_at, is_deleted, note, appointment_patient_record_id, appointment_status_id, appointment_type_id, dental_staff_id, doctor_id, patient_id) VALUES (NULL, '2024-07-01', '2024-06-28', b'0', 'hay', NULL, 2, 1, NULL, 1, 1);
INSERT INTO appointment (appointment_id, appointment_date, create_at, is_deleted, note, appointment_patient_record_id, appointment_status_id, appointment_type_id, dental_staff_id, doctor_id, patient_id) VALUES (NULL, '2024-07-01', '2024-06-28', b'0', 'hay', NULL, 3, 1, NULL, 1, 1);

INSERT INTO doctor_unavailability (doctor_unavailability_id, description, is_deleted, time_of_shift_id, date, appointment_id) VALUES (NULL, 'Bận', b'0', 1, '2024-07-01 07:30:00.000000', 1);
INSERT INTO doctor_unavailability (doctor_unavailability_id, description, is_deleted, time_of_shift_id, date, appointment_id) VALUES (NULL, 'Bận', b'0', 2, '2024-07-01 08:30:00.000000', 2);
INSERT INTO doctor_unavailability (doctor_unavailability_id, description, is_deleted, time_of_shift_id, date, appointment_id) VALUES (NULL, 'Bận', b'0', 3, '2024-07-01 08:30:00.000000', 3);
