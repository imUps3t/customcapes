# customcapes
Acts as a "proxy" between OptiFine's cape server and Minecraft.

This should work on any OS, I used File.separator for filesystem access to ensure that it will work with any operating system (Windows, Linux, Mac)

There is only one requirement:

- You must add an entry in /etc/hosts (Or C:\Windows\System32\drivers\etc\hosts for Windows) as so:

  127.0.0.1 s.optifine.net

This will force your operating system to re-route all traffic from s.optifine.net (OptiFine's cape server) to localhost (127.0.0.1, where the server is hosted.)
