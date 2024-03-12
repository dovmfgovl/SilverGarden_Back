<img width="382" alt="스크린샷 2024-03-12 오후 12 41 45" src="https://github.com/Suyeon12345/SilverGarden_Back/assets/144109053/373aedcd-724f-49f9-9612-b36d9bd482e5">
<br>

## 제작중입니다

## 👋 소개

실버가든 프로젝트 백엔드입니다.

<br>

## 👥 멤버

<div align="center">

|                                                                **박정원**                                                                |                                                                **안수연**                                                                |                                                                **이지연**                                                                |                                                                **이슬기**                                                                |                                                                **고태규**                                                                |
| :--------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/Suyeon12345/SilverGarden_Front/assets/144109053/388fde3e-1c74-4f93-8d49-887ef2698df3" height=150 width=150> | <img src="https://github.com/Suyeon12345/SilverGarden_Front/assets/144109053/0330a1c3-0f60-4569-814f-540401ab0346" height=150 width=150> | <img src="https://github.com/Suyeon12345/SilverGarden_Front/assets/144109053/0146902c-087f-48c9-a601-b4cb2fd9ebdd" height=150 width=150> | <img src="https://github.com/Suyeon12345/SilverGarden_Front/assets/144109053/ec35ce79-e1ce-42a1-88ea-4270fa3e1a87" height=150 width=150> | <img src="https://github.com/Suyeon12345/SilverGarden_Front/assets/144109053/f90f98f4-1dbf-42e5-bbb1-520a7dd36cc4" height=150 width=150> |

</div>


## 🗂️ API

### Admins

| API                                                                                                                                               | Request              | Response                                    |
| ------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------- | ------------------------------------------- |
| <img src="https://img.shields.io/badge/%7F%20%20GET%20%20%20%7F-/admins/all-e1e1e1?labelColor=46C487&style=flat-square" height="25"/>             | none                 | user_id, email, name, tel, status, reg_date |
| <img src="https://img.shields.io/badge/%7F%20%20POST%20%7F-/admins/login-e1e1e1?labelColor=219BFD&style=flat-square" height="25"/>                | email, password      | none                                        |
| <img src="https://img.shields.io/badge/%7F%20%20PUT%20%20%20%7F-/admins/edit/:user_id-e1e1e1?labelColor=F89331&style=flat-square" height="25"/>   | user_id, email, name | none                                        |
| <img src="https://img.shields.io/badge/%7F%20%20PUT%20%20%20%7F-/admims/getout/:user_id-e1e1e1?labelColor=F89331&style=flat-square" height="25"/> | user_id              | none                                        |

### Members

| <div style="width:250px">API</div>                                                                                                                 | Request                                    | Response                                            |
| -------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------ | --------------------------------------------------- |
| <img src="https://img.shields.io/badge/%7F%20%20GET%20%20%20%7F-/members/all-e1e1e1?labelColor=46C487&style=flat-square" height="25"/>             | none                                       | user_id, email, name, tel, result, status, reg_date |
| <img src="https://img.shields.io/badge/%7F%20%20GET%20%20%20%7F-/members/address/:userid-e1e1e1?labelColor=46C487&style=flat-square" height="25"/> | user_id, name, tel, addr1, addr2, reg_date | none                                                |
| <img src="https://img.shields.io/badge/%7F%20%20POST%20%7F-/members/join-e1e1e1?labelColor=219BFD&style=flat-square" height="25"/>                 | email, password, name                      | none                                                |
| <img src="https://img.shields.io/badge/%7F%20%20POST%20%7F-/members/edit/:user_id-e1e1e1?labelColor=219BFD&style=flat-square" height="25"/>        | user_id, email, name, tel                  | none                                                |
| <img src="https://img.shields.io/badge/%7F%20%20POST%20%7F-/members/getout/:user_id-e1e1e1?labelColor=219BFD&style=flat-square" height="25"/>      | user_id                                    | none                                                |
| <img src="https://img.shields.io/badge/%7F%20%20POST%20%7F-/members/newaddr/:userid-e1e1e1?labelColor=219BFD&style=flat-square" height="25"/>      | user_id, tel, addr1, addr2, name           | none                                                |

### Orders

| <div style="width:250px">API</div>                                                                                                                  | Request                                                  | Response                                                                                                                              |
| --------------------------------------------------------------------------------------------------------------------------------------------------- | -------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| <img src="https://img.shields.io/badge/%7F%20%20GET%20%20%20%7F-/orders/:user_id/:prod_id-e1e1e1?labelColor=46C487&style=flat-square" height="25"/> | userid, prodid                                           | order_id, user_id, prod_id                                                                                                            |
| <img src="https://img.shields.io/badge/%7F%20%20GET%20%20%20%7F-/orders/all-e1e1e1?labelColor=46C487&style=flat-square" height="25"/>               | none                                                     | orders.order_id, orders.order_price, order_date, orders.order_status, members.name, members.email, products.name, products.info_photo |
| <img src="https://img.shields.io/badge/%7F%20%20POST%20%7F-/orders/post-e1e1e1?labelColor=219BFD&style=flat-square" height="25"/>                   | order_price, order_select, order_count, prod_id, user_id | none                                                                                                                                  |
| <img src="https://img.shields.io/badge/%7F%20%20PUT%20%20%20%7F-/orders/:order_id-e1e1e1?labelColor=F89331&style=flat-square" height="25"/>         | order_id, order_date, order_price, order_status          | none                                                                                                                                  |

### Products

| <div style="width:250px">API</div>                                                                                                           | Request                                                                     | Response                                                                                                                               |
| -------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| <img src="https://img.shields.io/badge/%7F%20%20GET%20%20%20%7F-/products/all-e1e1e1?labelColor=46C487&style=flat-square" height="25"/>      | none                                                                        | prod_id, name, stock, status, price, category, thumbnail_photo, info_photo, prod_info, prod_feature, reg_date, review_count, stars_avg |
| <img src="https://img.shields.io/badge/%7F%20%20GET%20%20%20%7F-/products/:proid-e1e1e1?labelColor=46C487&style=flat-square" height="25"/>   | prod_id                                                                     | prod_id, name, stock, status, price, category, thumbnail_photo, info_photo, prod_info, prod_feature, reg_date, review_count, stars_avg |
| <img src="https://img.shields.io/badge/%7F%20%20GET%20%20%20%7F-/products/main-e1e1e1?labelColor=46C487&style=flat-square" height="25"/>     | none                                                                        | prod_id, name, stock, status, price, category, thumbnail_photo, info_photo, prod_info, prod_feature, reg_date, review_count, stars_avg |
| <img src="https://img.shields.io/badge/%7F%20%20POST%20%7F-/products-e1e1e1?labelColor=219BFD&style=flat-square" height="25"/>               | name, stock, price, category, thumImage, infoImage, prod_info, prod_feature | none                                                                                                                                   |
| <img src="https://img.shields.io/badge/%7F%20%20PUT%20%20%20%7F-/products/:prod_id-e1e1e1?labelColor=F89331&style=flat-square" height="25"/> | prod_id, name, stock, status                                                | none                                                                                                                                   |

### Reviews

| <div style="width:250px">API</div>                                                                                                       | Request                             | Response                                                                                 |
| ---------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------- | ---------------------------------------------------------------------------------------- |
| <img src="https://img.shields.io/badge/%7F%20%20GET%20%20%20%7F-/review/admin-e1e1e1?labelColor=46C487&style=flat-square" height="25"/>  | none                                | r.review_id, name, m.email, r.review_text, r.stars, write_date                           |
| <img src="https://img.shields.io/badge/%7F%20%20GET%20%20%20%7F-/review/:proid-e1e1e1?labelColor=46C487&style=flat-square" height="25"/> | prod_id                             | r.review_id, m.email, p.name, m.name, r.review_text, r.stars, write_date, r.review_photo |
| <img src="https://img.shields.io/badge/%7F%20%20POST%20%7F-/reviews/write-e1e1e1?labelColor=219BFD&style=flat-square" height="25"/>      | review_text, review_photo, order_id | none                                                                                     |
| <img src="https://img.shields.io/badge/DELETE-/reviews/del/:review_id-e1e1e1?labelColor=F52E39&style=flat-square" height="25"/>          | review_id                           | none                                                                                     |

<br>

## ⚙️ 파일 구조

```
├── README.md
├── _files
│   └── _logs
├── app.js
├── controllers
│   ├── Admins.js
│   ├── Members.js
│   ├── Orders.js
│   ├── Products.js
│   └── Reviews.js
├── exceptions
│   ├── BadRequestException.js
│   ├── PageNotFoundException.js
│   └── RuntimeException.js
├── helper
│   ├── FileHelper.js
│   ├── LogHelper.js
│   ├── RegexHelper.js
│   ├── UtilHelper.js
│   ├── WebHelper.js
│   ├── \_config.js
│   └── key.json
├── hook.sh
├── package-lock.json
├── package.json
├── public
│   └── favicon.png
├── resource
│   └── Dump.sql
└── webhook.js
```
