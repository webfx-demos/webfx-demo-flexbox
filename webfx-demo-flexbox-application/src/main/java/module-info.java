// File managed by WebFX (DO NOT EDIT MANUALLY)

module webfx.demo.flexbox.application {

    // Direct dependencies modules
    requires javafx.controls;
    requires javafx.graphics;
    requires webfx.extras.panes;

    // Exported packages
    exports dev.webfx.demo.flexbox;

    // Provided services
    provides javafx.application.Application with dev.webfx.demo.flexbox.FlexBoxApplication;

}