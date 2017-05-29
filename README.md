# CXF_Frame
Encapsulates the CXF framework (publishing platform), and the business module is plugged into the platform as a SDK

purpose:
  1. Simplify the WebService publishing process
  2. Publish a WebService external, you can include a large number of business modules
  3. Decoupling platform and business module
  4. Business team developers only need to care about the corresponding business module, do not care about the realization of the platform,
  5. Business module pluggable


CXFFrame
CXFFrame是在Apache CXF的基础上进一步封装的平台,业务模块以SDK的形式加入平台内.

目的:
1.简化WebService发布过程
2.对外发布一个WebService,即可包含大量业务模块
3.解耦平台 与 业务模块
4.业务组开发人员只需要关心对应的业务模块,不用关心平台内部的实现,
5.业务模块可插拔
