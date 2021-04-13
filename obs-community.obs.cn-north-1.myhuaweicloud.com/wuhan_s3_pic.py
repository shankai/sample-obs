import hashlib  
import hmac  
import binascii  
 
SK = 'dI3VwGMnwZ18msKnnkuNkm8cWNFW42ZKg20uekac'   


canonical_string = '{ "expiration": "2021-04-21T12:00:00.000Z", "conditions": [ { "bucket": "icos-registry" }, [ "eq", "$x-obs-acl", "public-read-write" ], [ "eq", "$key", "tile39proxy.yml" ] ] }'

policybase64 = binascii.b2a_base64(canonical_string.encode('utf-8'))  
policybase64 = policybase64[:-1].decode('UTF-8')  

print(policybase64)  
hashed = hmac.new(SK.encode('UTF-8'), policybase64.encode('UTF-8'), hashlib.sha1)  
encode_canonical = binascii.b2a_base64(hashed.digest())[:-1].decode('UTF-8')  

print(encode_canonical) 


#curl --location --request POST 'http://icos-registry.obs.cn-north-4.myhuaweicloud.com' \
#--header 'Authorization: OBS TTEZE3JFWYJPCWY1KAB2:1CP1pCsXZbtaSTtD2EC0ujiOjpM=' \
#--form 'key=gxd.png' \
#--form 'AccessKeyId=TTEZE3JFWYJPCWY1KAB2' \
#--form 'x-obs-acl=public-read-write' \
#--form 'policy=eyAiZXhwaXJhdGlvbiI6ICIyMDIwLTEyLTIxVDEyOjAwOjAwLjAwMFoiLCAiY29uZGl0aW9ucyI6IFsgeyAiYnVja2V0IjogImljb3MtcmVnaXN0cnkiIH0sIFsgImVxIiwgIiR4LW9icy1hY2wiLCAicHVibGljLXJlYWQtd3JpdGUiIF0sIFsgImVxIiwgIiRrZXkiLCAiZ3hkLnBuZyIgXSBdIH0=' \
#--form 'signature=TYkwEteAPM39J2WzDOF/9PQ1GU8=' \
#--form 'file=@/Users/guoxudong/Desktop/gxd.png'
