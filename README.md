## 上传
请求示例：
```
curl --location --request POST "http://icos-registry.obs.cn-north-4.myhuaweicloud.com" \
--form 'key="tile39proxy.yml"' \
--form 'AccessKeyId="TTEZE3JFWYJPCWY1KAB2"' \
--form 'x-obs-acl="public-read-write"' \
--form 'policy="eyJleHBpcmF0aW9uIjoiMjAyMS0wNC0xM1QxMjowMzowMy4wNjFaIiwiY29uZGl0aW9ucyI6W3siYnVja2V0IjoiaWNvcy1yZWdpc3RyeSJ9LFsiZXEiLCIkeC1vYnMtYWNsIiwicHVibGljLXJlYWQtd3JpdGUiXSxbImVxIiwiJGtleSIsInRpbGUzOXByb3h5LnltbCJdXX0="' \
--form 'signature="DORIqO4DjDoHsRinAyJBZKZEOFA="' \
--form 'file=@"/Users/DK/Desktop/tile39proxy.yml"'
```
参数说明： 
- `key`: 对象 Key
- `AccessKeyId`: AK
- `x-obs-acl`: `public-read-write`
- `policy`: 通过脚本/程序生成的策略(Base64 编码)
- `signature`: 通过脚本/程序生成的签名(Base64 编码)
- `file` 文件对象

`policy`与`signature`通过 `obs-community.obs.cn-north-1.myhuaweicloud.com/wuhan_s3_pic.py` 生成；或执行本工程 `Main.java`。

## 查询

### 生成Header鉴权签名

浏览器打开以下地址：
```
https://obs-community.obs.cn-north-1.myhuaweicloud.com/sign/header_signature.html
```
输入参数（示例）：
- `AK`: `TTEZE3JFWYJPCWY1KAB2`  AccessKey
- `SK`: `dI3VwGMnwZ18msKnnkuNkm8cWNFW42ZKg20uekac` SecretKey
- `HTTP-Verb`: `GET`
- `Date`: `${Now}`
- `Canonicalized Resource`: `/icos-registry/test.test` e.g. ${bucket}/${object_key}

点击`生成Header鉴权签名`按钮。

### 查询

```
curl --location --request GET "http://icos-registry.obs.cn-north-4.myhuaweicloud.com/${object_key}" \
--header 'Authorization: OBS TTEZE3JFWYJPCWY1KAB2:wlx4eADXcybhjMsslQcecyLbsq4=' \
--header 'Date: Tue, 13 Apr 2021 03:59:57 GMT'
```

将请求头:
- `Authorization` 替换为上一步生成的 `${Authorization}`
- `Date` 替换为上一步使用的 `${Now}`

请求地址中的 `${object_key}` 替换为目标对象 Key。

## 其他

`obs-community.obs.cn-north-1.myhuaweicloud.com/OBS.postman_collection.json` 文件是 Postman 请求示例脚本。