@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  spring-boot-mywebflux startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and SPRING_BOOT_MYWEBFLUX_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\netty-transport-4.1.12.Final.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.12.Final.jar;%APP_HOME%\lib\jackson-annotations-2.9.0.pr3.jar;%APP_HOME%\lib\netty-codec-4.1.12.Final.jar;%APP_HOME%\lib\dom4j-1.6.1.jar;%APP_HOME%\lib\hibernate-core-5.2.10.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.12.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.12.Final.jar;%APP_HOME%\lib\spring-context-support-5.0.0.RC2.jar;%APP_HOME%\lib\reactive-streams-1.0.0.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.25.jar;%APP_HOME%\lib\reactor-ipc-0.6.2.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-2.0.0.M2.jar;%APP_HOME%\lib\reactor-core-1.0.1.RELEASE.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.25.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.0.0.M2.jar;%APP_HOME%\lib\spring-jcl-5.0.0.RC2.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\spring-boot-starter-2.0.0.M2.jar;%APP_HOME%\lib\jackson-core-2.9.0.pr3.jar;%APP_HOME%\lib\spring-tx-5.0.0.RC2.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.9.0.pr3.jar;%APP_HOME%\lib\asm-5.0.3.jar;%APP_HOME%\lib\spring-boot-starter-reactor-netty-2.0.0.M2.jar;%APP_HOME%\lib\json-path-2.2.0.jar;%APP_HOME%\lib\reactor-netty-0.6.4.RELEASE.jar;%APP_HOME%\lib\spring-data-jpa-2.0.0.M4.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\classmate-1.3.3.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\reactor-core-3.1.0.M2.jar;%APP_HOME%\lib\netty-resolver-4.1.12.Final.jar;%APP_HOME%\lib\spring-boot-starter-aop-2.0.0.M2.jar;%APP_HOME%\lib\hibernate-validator-5.4.1.Final.jar;%APP_HOME%\lib\spring-boot-mywebflux-0.1.0-SNAPSHOT.jar;%APP_HOME%\lib\netty-transport-native-unix-common-4.1.12.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.12.Final.jar;%APP_HOME%\lib\spring-boot-starter-logging-2.0.0.M2.jar;%APP_HOME%\lib\spring-jdbc-5.0.0.RC2.jar;%APP_HOME%\lib\spring-core-5.0.0.RC2.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.9.0.pr3.jar;%APP_HOME%\lib\spring-orm-5.0.0.RC2.jar;%APP_HOME%\lib\jboss-transaction-api_1.2_spec-1.0.1.Final.jar;%APP_HOME%\lib\aspectjweaver-1.8.10.jar;%APP_HOME%\lib\spring-boot-starter-json-2.0.0.M2.jar;%APP_HOME%\lib\HikariCP-2.6.2.jar;%APP_HOME%\lib\spring-aop-5.0.0.RC2.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-2.0.0.M2.jar;%APP_HOME%\lib\netty-codec-http-4.1.12.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.1.Final.jar;%APP_HOME%\lib\spring-web-5.0.0.RC2.jar;%APP_HOME%\lib\json-smart-2.2.1.jar;%APP_HOME%\lib\jandex-2.0.3.Final.jar;%APP_HOME%\lib\jackson-databind-2.9.0.pr3.jar;%APP_HOME%\lib\postgresql-42.1.1.jar;%APP_HOME%\lib\spring-expression-5.0.0.RC2.jar;%APP_HOME%\lib\hibernate-jpa-2.1-api-1.0.0.Final.jar;%APP_HOME%\lib\netty-handler-4.1.12.Final.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\spring-boot-2.0.0.M2.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.0.1.Final.jar;%APP_HOME%\lib\snakeyaml-1.18.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\netty-common-4.1.12.Final.jar;%APP_HOME%\lib\javassist-3.21.0-GA.jar;%APP_HOME%\lib\spring-aspects-5.0.0.RC2.jar;%APP_HOME%\lib\spring-beans-5.0.0.RC2.jar;%APP_HOME%\lib\spring-boot-starter-webflux-2.0.0.M2.jar;%APP_HOME%\lib\spring-webflux-5.0.0.RC2.jar;%APP_HOME%\lib\javax.transaction-api-1.2.jar;%APP_HOME%\lib\spring-context-5.0.0.RC2.jar;%APP_HOME%\lib\accessors-smart-1.1.jar;%APP_HOME%\lib\disruptor-3.2.0.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.9.0.pr3.jar;%APP_HOME%\lib\spring-data-commons-2.0.0.M4.jar;%APP_HOME%\lib\reactor-spring-1.0.1.RELEASE.jar

@rem Execute spring-boot-mywebflux
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SPRING_BOOT_MYWEBFLUX_OPTS%  -classpath "%CLASSPATH%" com.webflux.MyWebFluxApplication %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SPRING_BOOT_MYWEBFLUX_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SPRING_BOOT_MYWEBFLUX_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
