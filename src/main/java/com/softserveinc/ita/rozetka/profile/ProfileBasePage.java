package com.softserveinc.ita.rozetka.profile;

import com.softserveinc.ita.rozetka.components.profile.ProfileSidebar;

public abstract class ProfileBasePage {

    public ProfileSidebar getProfileSideBar() {
        return new ProfileSidebar();
    }
}