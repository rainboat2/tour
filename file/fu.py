# -*- coding: utf-8 -*-
s = set()
with open("D:\\workplace\\tour\\file\\info.txt", "r") as f:
    lines = f.readlines()

for line in lines:
    a, b, c = line.split("——");
    s.update([a, b])

a = list(s)
a.sort()

with open("D:\\workplace\\tour\\file\\information.txt", "w", encoding="utf-8") as f:
    f.write(str(len(a)) + "\n")
    for item in a:
        # 名字 欢迎度 休息区 公厕 简介
        f.write("%s^%d^%d^%d^%s^%s\n" % (item, 0, 0, 0, "暂无说明", "img\\default.jpg"))
    f.write("<<\n")
    f.writelines(lines)
