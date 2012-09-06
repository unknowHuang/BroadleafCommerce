package org.broadleafcommerce.common.presentation;

import org.broadleafcommerce.common.presentation.client.AddMethodType;
import org.broadleafcommerce.common.presentation.client.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to describe a simple persistent collection
 * for use by the admin tool.
 *
 * @author Jeff Fischer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AdminPresentationCollection {

    /**
     * Optional - only required when targeting a metadata override
     * via application context xml.
     *
     * When a configuration key is present, the system will look for configuration
     * override specified in application context xml for this collection.
     *
     * @return the key tied to the override configuration
     */
    String configurationKey() default "";

    /**
     * Optional - field name will be used if not specified
     *
     * The friendly name to present to a user for this field in a GUI. If supporting i18N,
     * the friendly name may be a key to retrieve a localized friendly name using
     * the GWT support for i18N.
     *
     * @return the friendly name
     */
    String friendlyName() default "";

    /**
     * Optional - only required if you wish to apply security to this field
     *
     * If a security level is specified, it is registered with the SecurityManager.
     * The SecurityManager checks the permission of the current user to
     * determine if this field should be disabled based on the specified level.
     *
     * @return the security level
     */
    String securityLevel() default "";

    /**
     * Optional - fields are not excluded by default
     *
     * Specify if this field should be excluded from inclusion in the
     * admin presentation layer
     *
     * @return whether or not the field should be excluded
     */
    boolean excluded() default false;

    /**
     * Optional - only required if the collection grid UI
     * should be in read only mode
     *
     * Whether or not the collection can be edited
     *
     * @return Whether or not the collection can be edited
     */
    boolean mutable() default true;

    /**
     * Define whether or not added items for this
     * collection are acquired via search or construction.
     *
     * @return the item is acquired via lookup or construction
     */
    AddMethodType addType();

    /**
     * Optional - only required in the absence of a "mappedBy" property
     * on the JPA annotation
     *
     * For the target entity of this collection, specify the field
     * name that refers back to the parent entity.
     *
     * For collection definitions that use the "mappedBy" property
     * of the @OneToMany and @ManyToMany annotations, this value
     * can be safely ignored as the system will be able to infer
     * the proper value from this.
     *
     * @return the parent entity referring field name
     */
    String manyToField() default "";

    /**
     * Optional - only required if you want to specify ordering for this field
     *
     * The order in which this field will appear in a GUI relative to other collections from the same class
     *
     * @return the display order
     */
    int order() default 99999;

    /**
     * Optional - only required if you want the resulting collection grid element to
     * appear somewhere other than below the main detail form
     *
     * Specify a UI element Id to which the collection grid should be added. This is useful
     * if, for example, you want the resulting collection grid to appear in another tab, or
     * some other location in the admin tool UI.
     *
     * @return UI element Id to which the collection grid should be added
     */
    String targetUIElementId() default "";

    /**
     * Optional - unique name for the backing datasource. If unspecified, the datasource
     * name will be the JPA entity field name with "AdvancedCollectionDS" appended to the end.
     *
     * The datasource can be retrieved programatically in admin code via
     * PresenterSequenceSetupManager.getDataSource(..)
     *
     * @return unique name for the backing datasource
     */
    String dataSourceName() default "";

    /**
     * Optional - only required if you need to specially handle crud operations for this
     * specific collection on the server
     *
     * Custom string values that will be passed to the server during CRUB operations on this
     * collection. These criteria values can be detected in a custom persistence handler
     * (@CustomPersistenceHandler) in order to engage special handling through custom server
     * side code for this collection.
     *
     * @return the custom string array to pass to the server during CRUD operations
     */
    String[] customCriteria() default {};

    /**
     * Optional - only required if a special operation type is required for a CRUD operation. This
     * setting is not normally changed and is an advanced setting
     *
     * The operation type for a CRUD operation
     *
     * @return the operation type
     */
    AdminPresentationOperationTypes operationTypes() default @AdminPresentationOperationTypes(addType = OperationType.BASIC, fetchType = OperationType.BASIC, inspectType = OperationType.BASIC, removeType = OperationType.BASIC, updateType = OperationType.BASIC);

}
