package cz.cvut.fel.aui.uiml.model;

import com.codingcrayons.aspectfaces.annotations.UiIgnore;
import com.codingcrayons.aspectfaces.annotations.UiLink;

/**
 * Created by Tomáš on 28.12.13.
 */
public class Navigation
{
    private String outcome;

    private String label;

    public Navigation(String outcome, String label)
    {
        this.outcome = outcome;
        this.label = label;
    }

    @UiLink
    public String getOutcome()
    {
        return outcome;
    }

    @UiIgnore
    public String getLabel()
    {
        return label;
    }
}
