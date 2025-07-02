
import { useState } from 'react';
import { User, ChevronDown } from 'lucide-react';
import { Link } from 'react-router-dom';

const UserDropdown = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div className="relative">
      <Link
        to="/my-page"
        className="flex items-center gap-2 text-gray-700 hover:text-gray-900 transition-colors duration-200"
      >
        <User size={18} />
        <span className="text-sm">프로필</span>
      </Link>
    </div>
  );
};

export default UserDropdown;
