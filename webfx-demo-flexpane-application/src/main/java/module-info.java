// File managed by WebFX (DO NOT EDIT MANUALLY)

module webfx.demo.flexpane.application {

    // Direct dependencies modules
    requires javafx.controls;
    requires javafx.graphics;
    requires webfx.extras.panes;

    // Exported packages
    exports dev.webfx.demo.flexpane;

    // Provided services
    provides javafx.application.Application with dev.webfx.demo.flexpane.FlexPaneApplication;

}