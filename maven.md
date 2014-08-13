# convert eclipse project

[Maven Eclipse Plugin](http://maven.apache.org/plugins/maven-eclipse-plugin/)

workspaceの設定:

    $ mvn -Declipse.workspace=<path to workspace> eclipse:configure-workspace

eclipseプロジェクトの生成:

    $ mvn eclipse:eclipse

