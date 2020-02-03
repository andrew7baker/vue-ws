

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_config";
CREATE TABLE "public"."sys_config" (
  "id" bigint NOT NULL ,
  "name" varchar(200) ,
  "code" varchar(200) NOT NULL ,
  "dict_flag" char(1) NOT NULL ,
  "dict_type_id" bigint  DEFAULT NULL,
  "value" varchar(200) NOT NULL  ,
  "remark" varchar(200) DEFAULT NULL ,
  "create_time" TIMESTAMP DEFAULT NULL  ,
  "create_user" bigint DEFAULT NULL  ,
  "update_time" TIMESTAMP DEFAULT NULL ,
  "update_user" bigint DEFAULT NULL
)
;
ALTER TABLE "public"."sys_config" OWNER TO "smt";

-- ----------------------------
-- Primary Key structure for table production
-- ----------------------------
ALTER TABLE "public"."sys_config" ADD CONSTRAINT "sys_config_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO "sys_config" VALUES (1143324237579165697, '验证码开关', 'GUNS_KAPTCHA_OPEN', 'Y', 1106120265689055233, 'DISABLE', '是否开启验证码', '2019-06-24 12:46:43', 1, '2019-06-25 09:04:42', 1);
INSERT INTO "sys_config" VALUES (1143386834613694465, '阿里云短信的keyId', 'GUNS_SMS_ACCESSKEY_ID', 'N', NULL, 'xxxkey', '阿里云短信的密钥key', '2019-06-25 13:13:59', 1, '2019-06-25 13:19:21', 1);
INSERT INTO "sys_config" VALUES (1143386953933254657, '阿里云短信的secret', 'GUNS_SMS_ACCESSKEY_SECRET', 'N', NULL, 'xxxsecret', '阿里云短信的secret', '2019-06-25 13:14:28', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1143387023449649154, '阿里云短信的签名', 'GUNS_SMS_SIGN_NAME', 'N', NULL, 'xxxsign', '阿里云短信的签名', '2019-06-25 13:14:44', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1143387131109044225, '阿里云短信登录的模板号', 'GUNS_SMS_LOGIN_TEMPLATE_CODE', 'N', NULL, 'SMS_XXXXXX', '阿里云短信登录的模板号', '2019-06-25 13:15:10', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1143387225019510785, '验证码短信失效时间', 'GUNS_SMS_INVALIDATE_MINUTES', 'N', NULL, '2', '验证码短信失效时间', '2019-06-25 13:15:32', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1143468689664876546, '管理系统名称', 'GUNS_SYSTEM_NAME', 'N', NULL, 'Guns快速开发平台', '管理系统名称', '2019-06-25 18:39:15', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1143468867767607297, '默认系统密码', 'GUNS_DEFAULT_PASSWORD', 'N', NULL, '111111', '默认系统密码', '2019-06-25 18:39:57', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1143469008025133058, 'OAuth2登录用户的账号标识', 'GUNS_OAUTH2_PREFIX', 'N', NULL, 'oauth2', 'OAuth2登录用户的账号标识', '2019-06-25 18:40:31', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1145207130463191041, '顶部导航条是否开启', 'GUNS_DEFAULT_ADVERT', 'Y', 1106120265689055233, 'ENABLE', '顶部Guns广告是否开启', '2019-06-30 13:47:11', 1, '2019-06-30 13:47:20', 1);
INSERT INTO "sys_config" VALUES (1145915627211370498, 'Guns发布的编号', 'GUNS_SYSTEM_RELEASE_VERSION', 'N', NULL, '20191025', '用于防止浏览器缓存相关的js和css', '2019-07-02 12:42:30', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1145915627211370499, '文件上传路径', 'GUNS_FILE_UPLOAD_PATH', 'N', NULL, '/Users/stylefeng/tmp/gunsTempFiles/', '文件上传默认目录', '2019-08-30 09:10:40', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1145915627211370500, 'BPMN文件上传路径', 'GUNS_BPMN_FILE_UPLOAD_PATH', 'N', NULL, '/Users/stylefeng/tmp/gunsTempFiles/', '工作流文件上传默认目录', '2019-08-30 09:10:40', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1145915627211370501, '获取系统地密钥过期时间', 'GUNS_JWT_SECRET_EXPIRE', 'N', NULL, '86400', '获取系统地密钥过期时间（单位：秒），默认1天', '2019-10-16 23:02:39', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1145915627211370502, '获取token的header标识', 'GUNS_TOKEN_HEADER_NAME', 'N', NULL, 'Authorization', '获取token的header标识', '2019-10-16 23:02:39', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1145915627211370503, '获取租户是否开启的标识', 'GUNS_TENANT_OPEN', 'Y', 1106120265689055233, 'DISABLE', '获取租户是否开启的标识，默认是关的', '2019-10-16 23:02:39', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1202256370647601153, 'ftp用户名', 'FTP_USER', 'N', NULL, 'uu188912', 'bccwFTP', '2019-12-05 00:00:30', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1202256551690539010, 'ftp密码', 'FTP_PASS', 'N', NULL, 'xzmF0KHCBhYyeKH', '', '2019-12-05 00:01:13', 1, NULL, NULL);
INSERT INTO "sys_config" VALUES (1202256698843500545, 'FTP地址', 'FTP_ADD', 'N', NULL, '47.52.110.120', '', '2019-12-05 00:01:49', 1, '2019-12-05 00:02:07', 1);
INSERT INTO "sys_config" VALUES (1202257741685886978, 'FTP目录', 'FTP_DIR', 'N', NULL, '2-yimi', '', '2019-12-05 00:05:57', 1, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
