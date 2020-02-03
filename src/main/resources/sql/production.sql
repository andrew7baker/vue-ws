/*
 Navicat Premium Data Transfer

 Source Server         : tx
 Source Server Type    : PostgreSQL
 Source Server Version : 110006
 Source Host           : tx:5432
 Source Catalog        : smt
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110006
 File Encoding         : 65001

 Date: 03/02/2020 01:04:56
*/


-- ----------------------------
-- Table structure for production
-- ----------------------------
DROP TABLE IF EXISTS "public"."production";
CREATE TABLE "public"."production" (
  "id" int8 NOT NULL,
  "version" varchar(255) COLLATE "pg_catalog"."default",
  "power_time" int4,
  "place_time" int4,
  "wait_time" int4,
  "run_time" int4,
  "stop_time" int4,
  "idle_time" int4,
  "in_wait_time" int4,
  "out_wait_time" int4,
  "trans_time" int4,
  "wrong_stop_time" int4,
  "error_stop_t_ime" int4,
  "wrong_stop_count" int4,
  "error_stop_count" int4,
  "panel_in_count" int4,
  "panel_out_count" int4,
  "panel_count" int4,
  "p_cb_count" int4,
  "error_pcb" int4,
  "skip_pcb" int4,
  "operation_rate" float4,
  "placement_rate" float4,
  "mean_time" float4,
  "real_time" float4,
  "transfer_time" float4,
  "place_count" int4,
  "the_efficiency" float4
)
;
ALTER TABLE "public"."production" OWNER TO "smt";

-- ----------------------------
-- Primary Key structure for table production
-- ----------------------------
ALTER TABLE "public"."production" ADD CONSTRAINT "production_pkey" PRIMARY KEY ("id");
