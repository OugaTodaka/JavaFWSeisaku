INSERT INTO CLASS_NUM(SCHOOL_CD, CLASS_NUM) VALUES
('tes', '101'), ('tes', '102'),
('tes', '201'), ('tes', '202'),
('tes', '301'), ('tes', '302'),
('san', '101'), ('san', '102'),
('san', '201'), ('san', '202');

INSERT INTO SCHOOL(CD, NAME) VALUES
('tes', 'テスト校'),
('san','サンプル校'),
('del','削除済み');

INSERT INTO STUDENT(NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES
('2231111', '大原太郎', 2022, '301', TRUE, 'tes'),
('2347017', '戸高央雅', 2023, '202', TRUE, 'san');

INSERT INTO SUBJECT(SCHOOL_CD, CD, NAME) VALUES
('tes', 'A01', '国語'), ('tes', 'A02', '数学'), ('tes', 'A03', '英語'),
('san', 'A01', '国語'), ('san', 'A02', '数学'), ('san', 'A03', '英語');

INSERT INTO TEACHER(TEACHER_ID, PASSWORD, NAME, SCHOOL_CD) VALUES
('admin1', 'password', '管理者1', 'tes'),
('admin2', 'password', '管理者2', 'san');

INSERT INTO TEST(STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES
('2347017', 'A02', 'san', 1, 80, '202'),
('2347017', 'A03', 'san', 1, 95, '202'),
('2231111', 'A01', 'tes', 1, 90, '301'),
('2231111', 'A02', 'tes', 1, 85, '301');