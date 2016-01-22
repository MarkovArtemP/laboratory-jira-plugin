package ut.com.izpa.jira.plugins.laboratory;

import org.junit.Test;
import com.izpa.jira.plugins.laboratory.api.MyPluginComponent;
import com.izpa.jira.plugins.laboratory.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}