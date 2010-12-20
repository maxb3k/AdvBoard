package advboard.stripes;

import net.sourceforge.stripes.controller.NameBasedActionResolver;

/**
 * My specific Action Resolver.
 * Same as standard Action Resolver but case insensitive .
 * Bla.Bla.Bla.MyClass -> /myclass.action
 */

public class AdvBoardActionResolver extends NameBasedActionResolver
{
    @Override
    protected String getUrlBinding(String name)
    {
        return super.getUrlBinding(name).toLowerCase();
    }
}
