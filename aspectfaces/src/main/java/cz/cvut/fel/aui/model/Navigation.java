package cz.cvut.fel.aui.model;

import com.codingcrayons.aspectfaces.annotations.UiLabel;
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

    @UiLabel
    public String getLabel()
    {
        return label;
    }
}
