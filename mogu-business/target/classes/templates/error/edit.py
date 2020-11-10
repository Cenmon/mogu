import glob,re

ls = glob.glob("*.html")
# print(dir(re))

for file in ls:
    f = open(file,"r",encoding="utf-8")
    fw = open("modify/"+file,"w",encoding="utf-8")
    for line in f:
        # if re.match(".*<link href=",line) and not re.match(".*@",line):
        #     print(file,line)
        if re.match("<html>",line):
            line = re.sub("<html>",'<html xmlns:th="http://www.thymeleaf.org">',line)
            fw.write(line)
            continue;
            # print(file,line);
        if re.match(".*<link href=",line):
            ls = re.findall('.*href="(.*)?" ',line)

            if not re.match(".*th:",line): # 未匹配到th，则加#
                line = re.sub("href","th:href",line);

            if not re.match(".*@",line): # 未匹配到#，则加#
                line = re.sub(ls[0],'@{/'+ls[0]+'}',line);
            # print(file,line)

            fw.write(line)
            continue;
            
        if re.match(".*<script src=",line):
            # print(line)
            ls = re.findall('.*src="(.*)?">',line)
            # print(ls)
            if not re.match(".*th:",line): # 未匹配到th，则加#
                line = re.sub("src","th:src",line);
            if not re.match(".*@",line): # 未匹配到#，则加#
                line = re.sub(ls[0],'@{/'+ls[0]+'}',line);
            # print(file,line)

            fw.write(line)
            continue;
        fw.write(line);
    fw.close();
    f.close();