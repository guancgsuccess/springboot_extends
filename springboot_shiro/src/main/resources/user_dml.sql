INSERT INTO `u_permission` VALUES (1, '/add', '添加用戶');
INSERT INTO `u_permission` VALUES (2, '/update', '修改用戶');
INSERT INTO `u_permission` VALUES (3, '/delete', '刪除用戶');
INSERT INTO `u_permission` VALUES (4, '/select', '查詢用戶');


INSERT INTO `u_role` VALUES (1, 'admin', '1');
INSERT INTO `u_role` VALUES (2, 'user', '2');
INSERT INTO `u_role` VALUES (3, 'shop', '3');


INSERT INTO `u_role_permission` VALUES (1, 1);
INSERT INTO `u_role_permission` VALUES (1, 2);
INSERT INTO `u_role_permission` VALUES (1, 3);
INSERT INTO `u_role_permission` VALUES (1, 4);

INSERT INTO `u_role_permission` VALUES (2, 1);
INSERT INTO `u_role_permission` VALUES (2, 2);
INSERT INTO `u_role_permission` VALUES (2, 3);

INSERT INTO `u_role_permission` VALUES (3, 4);


INSERT INTO `u_user_role` VALUES (2, 1);
INSERT INTO `u_user_role` VALUES (2, 2);

INSERT INTO `u_user_role` VALUES (1, 1);
INSERT INTO `u_user_role` VALUES (1, 2);
INSERT INTO `u_user_role` VALUES (1, 3);

INSERT INTO `u_user_role` VALUES (3, 3);

